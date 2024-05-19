package spring.learn.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import spring.learn.spring.model.Role;
import spring.learn.spring.model.User;
import spring.learn.spring.model.UserRole;
import spring.learn.spring.model.UserRoleId;
import spring.learn.spring.repository.RoleRepository;
import spring.learn.spring.repository.UserRepository;
import spring.learn.spring.repository.UserRoleRepository;
import spring.learn.spring.response.RolesIds;
import spring.learn.spring.response.StateResponse;
import spring.learn.spring.response.UserRoleMin;
import spring.learn.spring.service.UsersRolesService;
import spring.learn.spring.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsersRolesServiceImpl implements UsersRolesService {

	@Autowired
	UserRoleRepository userRoleRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;

	@Override
	public UserRole createUserRole(UserRoleMin userRoleMin) {
		UserRoleId userRoleId;
		UserRole userRole;
		Optional<User> user = userRepo.findById(userRoleMin.getUserId());
		Optional<Role> role = roleRepo.findById(userRoleMin.getRoleId());

		if (user.isPresent() && role.isPresent()) {
			userRoleId = new UserRoleId(user.get(), role.get());
			userRole = new UserRole(userRoleId);
			userRole.setLastUpdateOn(new Date());
			userRole.setCreatedOn(new Date());

			return userRoleRepo.save(userRole);
		}
		return null;
	}

	@Override
	public UserRole updateUserRole(UserRole userRole) {
		userRole.setLastUpdateOn(new Date());
		return userRoleRepo.save(userRole);
	}

	@Override
	public List<UserRole> getAllUsersRoles() {
		List<UserRole> userRole = new ArrayList<>();
		userRoleRepo.getAllUserRoles(Constants.STATE_ACTIVATED, Constants.STATE_DEACTIVATED).forEach(userRole::add);
		return userRole;
	}

	@Override
	public UserRole deleteUserRole(int userId, int roleId) {
		UserRoleId userRoleId = new UserRoleId();

		Optional<User> user = userRepo.findById(userId);
		Optional<Role> role = roleRepo.findById(roleId);
		if (user.isPresent() && role.isPresent()) {
			userRoleId.setUser(user.get());
			userRoleId.setRole(role.get());
		}
		UserRole userRoleToDelete = userRoleRepo.findByUserRoleId(userRoleId);
		if (userRoleToDelete != null) {
			userRoleRepo.delete(userRoleToDelete);
			return userRoleToDelete;
		}
		return null;
	}

	@Override
	public UserRole findUserRoleByUserIdAndRoleId(int userId, int roleId) {
		UserRoleId userRoleId = new UserRoleId();
		Optional<User> user = userRepo.findById(userId);
		Optional<Role> role = roleRepo.findById(roleId);
		if (user.isPresent() && role.isPresent()) {
			userRoleId.setUser(user.get());
			userRoleId.setRole(role.get());
		}

		return userRoleRepo.findByUserRoleId(userRoleId);
	}

	@Override
	public StateResponse assignRolesToUser(int userId, RolesIds rolesListWrapper) {
		Optional<User> user = userRepo.findById(userId);
		// Liste des id des roles envoyés dépuis le client
		List<Integer> roleIdsList = rolesListWrapper.getRolesIdsList();
		// Liste des roles envoyées dépuis le client
		List<Role> rolesToAssingList = new ArrayList<>();
		// Liste des roles de l'utilisateur
		List<Role> userRoleList;
		// Liste des roles de l'utilisateur qui ont encore été envoyées
		List<Role> rolesToMaintainList = new ArrayList<>();

		if (user.isPresent()) {
			// Initialiser le tableau de roles envoyés dépuis le client
			roleIdsList.forEach(roleId -> {
				Optional<Role> role = roleRepo.findById(roleId);
				if (role.isPresent() && role.get().getStatus() == Constants.STATE_ACTIVATED)
					rolesToAssingList.add(role.get());
			});
			// Initialiser la liste des roles de l'utilisateur
			userRoleList = this.getAllUserRoles(user.get().getId());

			// On parcours la liste des rôles à assigner à l'utilisateur, si l'utilisateur
			// ne possède pas le rôle à assigner, on lui assigne le rôle. Sinon si
			// l'utilisateur possède déjà le rôle à assigner, on le sauvegerde dans une
			// liste
			for (int i = 0; i < rolesToAssingList.size(); i++) {
				if (!userRoleList.contains(rolesToAssingList.get(i))) {
					UserRoleId userRoleId = new UserRoleId(user.get(), rolesToAssingList.get(i));
					UserRole userRole = new UserRole(userRoleId);
					userRoleRepo.save(userRole);
				} else {
					rolesToMaintainList.add(rolesToAssingList.get(i));
				}
			}
			// On enlève tout le rôles maintenus pour ne rester qu'avec les roles à
			// supprimer
			userRoleList.removeAll(rolesToMaintainList);

			// On supprime les roles qui ne sont plus assignés
			userRoleList.forEach(roleToDelete -> {
				UserRoleId userRoleId = new UserRoleId(user.get(), roleToDelete);
				UserRole userRole = new UserRole(userRoleId);
				userRoleRepo.delete(userRole);
			});

			return new StateResponse("SUCCEEDED");
		}
		return new StateResponse("FAILED");
	}

	@Override
	public StateResponse removeRolesToUser(int userId, RolesIds rolesListWrapper) {
		Optional<User> user = userRepo.findById(userId);
		List<Integer> roleIdsList = rolesListWrapper.getRolesIdsList();
		if (user.isPresent()) {
			roleIdsList.forEach(roleId -> {
				Role role = roleRepo.findById(roleId).get();
				if (role != null) {
					UserRoleId userRoleId = new UserRoleId(user.get(), role);
					UserRole userRole = userRoleRepo.findByUserRoleId(userRoleId);
					if (userRole != null) {
						userRoleRepo.delete(userRole);
					}
				}
			});
			return new StateResponse("SUCCEEDED");
		}
		return new StateResponse("FAILED");
	}

	@Override
	public List<Role> getAllUserRoles(int userId) {
		List<Role> rolesList = new ArrayList<>();
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			List<UserRole> userRoleList = userRoleRepo.findByUserRoleIdUser(user.get());
			userRoleList.forEach(userRole -> rolesList.add(userRole.getRole()));
		}
		return rolesList;
	}

	@Override
	public boolean isAdmin(List<UserRole> userRoles) {
		boolean isAdmin = false;
		for (UserRole usr : userRoles) {
			isAdmin = usr.getUserRoleId().getRole().getRoleName().equalsIgnoreCase("ADMIN");
			if (isAdmin)
				return isAdmin;
		}
		return isAdmin;
	}

}
