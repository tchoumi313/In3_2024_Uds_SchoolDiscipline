package spring.learn.spring.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import spring.learn.spring.exceptions.ApiException;
import spring.learn.spring.model.Role;
import spring.learn.spring.model.User;
import spring.learn.spring.model.UserRole;
import spring.learn.spring.model.UserRoleId;
import spring.learn.spring.repository.RoleRepository;
import spring.learn.spring.repository.UserRepository;
import spring.learn.spring.repository.UserRoleRepository;
import spring.learn.spring.response.AgGridResponse;
import spring.learn.spring.response.StatusResponse;
import spring.learn.spring.response.UserDto;
import spring.learn.spring.service.UserService;
import spring.learn.spring.util.Constants;
import spring.learn.spring.util.UserMapper;
import spring.learn.spring.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	private UserRepository usersRepository;

	@Autowired
	private UserRoleRepository userroleRepository;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String sender;

	public UserServiceImpl(UserRepository usersRepository, UserRoleRepository userroleRepository) {
		this.usersRepository = usersRepository;
		this.userroleRepository = userroleRepository;
	}

	public List<User> findByUserContainingIgnoreCase(String term) {
		return usersRepository.findByUserContainingIgnoreCase(term);
	}

	@Override
	public User createOrUpdateUser(UserDto userDto) throws ApiException {
		if (userDto.getId() != null) {
			User userFound = findUserById(userDto.getId());
			if (userFound == null)
				throw new ApiException(Constants.USER_NOT_FOUND_CODE, Constants.USER_NOT_FOUND);

			userFound.setUserEmail(userDto.getUserEmail());
			userFound.setFirstName(userDto.getFirstName());
			userFound.setLastName(userDto.getLastName());
			userFound.setUsername(userDto.getUsername());
			userFound.setComment(userDto.getComment());
			userFound.setLastUpdateOn(new Date());
			System.out.println(userDto);
			userFound.setRoleList(userDto.getRoleList());
			User userSave = usersRepository.save(userFound);

			if (userSave.getId() != null) {
				List<String> roleList = userDto.getRoleList();
				userroleRepository.deleteByUserRoleIdUser(userSave);
				
				Role role = roleRepo.findByRoleName(roleList.get(0));
		
				UserRoleId userRoleId = new UserRoleId(userSave, role);
				UserRole userRole = new UserRole(userRoleId);
				userSave.setUsersRolesList(new ArrayList<>());
				userSave.getUsersRolesList().add(userRole);
				
				return userSave;
			}

			return userSave;
		}

		User user = new User();

		user.setStatus(Constants.STATE_ACTIVATED);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setCreatedOn(new Date());
		user.setComment(userDto.getComment());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUsername(userDto.getUsername());

		User userSave = usersRepository.save(user);

		if (userSave.getId() != null) {
			Role role = roleRepo.findByRoleName(userDto.getRoleList().get(0));
			if(role == null){
				role = roleRepo.findByRoleName("USER");
			}
			if (role != null) {
				UserRoleId userRoleId = new UserRoleId(userSave, role);
				UserRole userRole = new UserRole(userRoleId);
				if (userSave.getUsersRolesList() == null) {
					userSave.setUsersRolesList(new ArrayList<>());
				}
				userSave.getUsersRolesList().add(userRole);
			}
			return userSave;
		}
		return userSave;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		usersRepository.getAllUser(Constants.STATE_ACTIVATED, Constants.STATE_DEACTIVATED).forEach(users::add);
		return users;
	}

	@Override
	public StatusResponse deleteUser(int userId) throws ApiException {
		Optional<User> opt = usersRepository.findById(userId);
		if (!opt.isPresent())
			throw new ApiException(Constants.USER_NOT_FOUND_CODE, Constants.USER_NOT_FOUND);

		opt.get().setStatus(Constants.STATE_DELETED);
		opt.get().setLastUpdateOn(new Date());
		usersRepository.save(opt.get());
		return new StatusResponse(true);
	}

	@Override
	public User findByUserName(String userName) {
		return usersRepository.findByUsername(userName);
	}

	@Override
	public User findByUserMAil(String userMail) {
		return usersRepository.findByUserEmail(userMail);
	}

	@Override
	public User findUserById(int userId) throws ApiException {
		Optional<User> opt = usersRepository.findById(userId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new ApiException(Constants.USER_NOT_FOUND_CODE, Constants.USER_NOT_FOUND);
		}
	}

	@Override
	public User updateUserPassword(int userId, String olpPassword, String newPassword) throws ApiException {
		User userToUpdate = this.findUserById(userId);

		if (passwordEncoder.matches(olpPassword, userToUpdate.getPassword())) {
			userToUpdate.setPassword(passwordEncoder.encode(newPassword));
			userToUpdate.setLastUpdateOn(new Date());
			return usersRepository.save(userToUpdate);
		} else {
			throw new ApiException(Constants.INVALID_INPUT, Constants.OLD_PASSWORD_NOT_MATCH);
		}
	}

	@Override
	public List<User> getAllArchivedUser() {
		return usersRepository.findAll()
				.stream()
				.filter(u -> u.getStatus() == Constants.STATE_ARCHIVE)
				.collect(Collectors.toList());
	}

	@Override
	public User createOrUpdateAdminUser(User usr) {
		if (usr.getId() != null) {
			usr.setLastUpdateOn(new Date());
		} else {
			usr.setStatus(Constants.STATE_ACTIVATED);
			Role role = roleRepo.findByRoleName("ADMIN");
			if (role != null) {
				UserRoleId userRoleId = new UserRoleId(usr, role);
				UserRole userRole = new UserRole(userRoleId);
				usr.getUsersRolesList().add(userRole);
			}
			usr.setPassword(passwordEncoder.encode(usr.getPassword()));
			usr.setCreatedOn(new Date());
		}
		return usersRepository.save(usr);
	}

	@Override
	public AgGridResponse records(Integer page, Integer size) {
		Page<User> users;

		Pageable pageRequest = PageRequest.of(page, size, Sort.by("id"));
		users = usersRepository.findAll(pageRequest);

		AgGridResponse agGridProjet = new AgGridResponse(users);

		agGridProjet.setContent(
				Utils.castToObject(UserMapper.mapToDtoList(users)));

		return agGridProjet;
	}

	@Override
	public AgGridResponse searchAll(Specification<User> specification, Integer page, Integer size) {
		if (size < 1) {
			throw new IllegalArgumentException("Page size is less than 1");
		}
		if (page < 0) {
			throw new IllegalArgumentException("Page number is less than 0");
		}
		Pageable pageRequest = PageRequest.of(page, size, Sort.by("id"));
		Page<User> params = usersRepository.findAll(specification, pageRequest);
		AgGridResponse agGridResponse = new AgGridResponse(params);
		agGridResponse.setContent(Utils.castToObject(UserMapper.mapToDtoList(params.getContent())));
		return agGridResponse;
	}

	@Override
	public StatusResponse updateUserStatus(UserDto userDto) throws ApiException {

		Optional<User> userFound = usersRepository.findById(userDto.getId());

		if (!userFound.isPresent())
			throw new ApiException(Constants.USER_NOT_FOUND_CODE, Constants.USER_NOT_FOUND);

		usersRepository.updateUserStatus(userDto.getId(), userDto.getStatus());
		return new StatusResponse(true);
	}

	@Override
	public void sendMailToResetPassword(String sender, String toEmail, String body, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		try {
			mailSender.send(message);
		} catch (MailException e) {
			throw new MailException("Simple mail not send to customer") {
			};
		}
	}

	@Override
	public StatusResponse addNewEmail(User user, String newEmail) throws ApiException {

		List<String> userDefaultEmails = usersRepository.findAll()
				.stream()
				.map(User::getUserEmail)
				.collect(Collectors.toList());

		List<String> groupedUsersEmailsList = usersRepository.findAll()
				.stream()
				.map(User::getEmailList)
				.filter(Objects::nonNull)
				.collect(Collectors.toList())
				.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());

		List<String> currentUserEmailsList = user.getEmailList();

		if (currentUserEmailsList == null) {
			currentUserEmailsList = new ArrayList<>();
		} else if (currentUserEmailsList.contains(newEmail)
				|| newEmail.equals(user.getUserEmail())
				|| userDefaultEmails.contains(newEmail)
				|| groupedUsersEmailsList.contains(newEmail)) {
			throw new ApiException(Constants.ITEM_ALREADY_EXIST, Constants.EMAIL_ALREADY_EXIST);
		}

		currentUserEmailsList.add(newEmail);
		user.setEmailList(currentUserEmailsList);
		usersRepository.save(user);
		return new StatusResponse(true);
	}

	@Override
	public StatusResponse resetPassword(String userEmail) throws ApiException {
		User userFound = findByUserMAil(userEmail);
		if (userFound == null) {
			throw new ApiException(Constants.USER_NOT_FOUND_CODE, Constants.USER_NOT_FOUND);
		} else {
			String generatedPassword = Utils.generateRandomUserPassword();
			String body = "Votre nouveau mot de passe est: " + generatedPassword
					+ ".\nVous pouvez vous connecter pour le changer.";
			String subject = "Rappel de mot de passe";

			sendMailToResetPassword(this.sender, userEmail, body, subject);
			userFound.setPassword(passwordEncoder.encode(generatedPassword));
			usersRepository.save(userFound);

			return new StatusResponse(true);
		}
	}

}
