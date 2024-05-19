package spring.learn.spring.service;

import spring.learn.spring.exceptions.ApiException;
import spring.learn.spring.model.User;
import spring.learn.spring.response.AgGridResponse;
import spring.learn.spring.response.StatusResponse;
import spring.learn.spring.response.UserDto;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public interface UserService {

	List<User> findByUserContainingIgnoreCase(String term);

	public User createOrUpdateUser(UserDto usr) throws ApiException;

	public List<User> getAllUser();

	public StatusResponse deleteUser(int userId) throws ApiException;

	public User findByUserName(String userName);

	public User findByUserMAil(String userMail);

	public User findUserById(int userId) throws ApiException;

	public User updateUserPassword(int userId, String olpPassword, String newPassword) throws ApiException;

	public List<User> getAllArchivedUser();

	public User createOrUpdateAdminUser(User usr);

	AgGridResponse records(Integer page, Integer size);

	AgGridResponse searchAll(Specification<User> specification, Integer page, Integer size);

	public StatusResponse updateUserStatus(UserDto userDto) throws ApiException;

	public void sendMailToResetPassword(String sender, String toEmail, String body, String subject);

	public StatusResponse addNewEmail(User user, String newEmail) throws ApiException;

	public StatusResponse resetPassword(String userEmail) throws ApiException;
}