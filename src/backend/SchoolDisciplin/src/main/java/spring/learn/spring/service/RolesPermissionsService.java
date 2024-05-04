package spring.learn.spring.service;

import java.util.List;

import spring.learn.spring.exceptions.ValgoException;
import spring.learn.spring.model.Permission;
import spring.learn.spring.model.RolePermission;
import spring.learn.spring.response.PermissionsIds;
import spring.learn.spring.response.RolePermissionMin;
import spring.learn.spring.response.StateResponse;


public interface RolesPermissionsService {
	public RolePermission createRolePermission(RolePermissionMin rolePermission) throws ValgoException;

	public RolePermission updateRolePermission(RolePermission rolePermission);

	public List<RolePermission> getAllRolesPermissions();

	public RolePermission deleteRolePermission(int roleId, int permissionId) throws ValgoException;

	public RolePermission findRolePermissionByRoleIdAndPermissionId(int roleId, int permissionId)
			throws ValgoException;

	public StateResponse assignPermissionsToRole(int roleId, PermissionsIds permissionsListWrapper);

	public StateResponse removePermissionsToRole(int roleId, PermissionsIds permissionsListWrapper);

	List<Permission> getAllRolePermissions(int roleId);
}
