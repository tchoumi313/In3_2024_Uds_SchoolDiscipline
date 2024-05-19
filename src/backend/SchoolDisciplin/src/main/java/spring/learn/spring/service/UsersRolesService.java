package spring.learn.spring.service;

import java.util.List;

import spring.learn.spring.model.Role;
import spring.learn.spring.model.UserRole;
import spring.learn.spring.response.RolesIds;
import spring.learn.spring.response.StateResponse;
import spring.learn.spring.response.UserRoleMin;


public interface UsersRolesService {
	public UserRole createUserRole(UserRoleMin userRole);

	public UserRole updateUserRole(UserRole userRole);

	public List<UserRole> getAllUsersRoles();

	public UserRole deleteUserRole(int userId, int roleId);

	public UserRole findUserRoleByUserIdAndRoleId(int userId, int roleId);

	public StateResponse assignRolesToUser(int userId, RolesIds rolesListWrapper);

	public StateResponse removeRolesToUser(int userId, RolesIds rolesListWrapper);

	List<Role> getAllUserRoles(int userId);

	public boolean isAdmin(List<UserRole> userRoles);
}
