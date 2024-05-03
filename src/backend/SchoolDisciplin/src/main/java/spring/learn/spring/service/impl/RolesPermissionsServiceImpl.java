package spring.learn.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import spring.learn.spring.exceptions.ValgoException;
import spring.learn.spring.model.Permission;
import spring.learn.spring.model.Role;
import spring.learn.spring.model.RolePermission;
import spring.learn.spring.model.RolePermissionId;
import spring.learn.spring.repository.PermissionRepository;
import spring.learn.spring.repository.RolePermissionRepository;
import spring.learn.spring.repository.RoleRepository;
import spring.learn.spring.response.PermissionsIds;
import spring.learn.spring.response.RolePermissionMin;
import spring.learn.spring.response.StateResponse;
import spring.learn.spring.service.RolesPermissionsService;
import spring.learn.spring.util.Constants;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RolesPermissionsServiceImpl implements RolesPermissionsService {

	private final RolePermissionRepository rolePermissionRepository;
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;

	public RolesPermissionsServiceImpl(
			RolePermissionRepository rolePermissionRepository,
			RoleRepository roleRepository,
			PermissionRepository permissionRepository) {
		this.rolePermissionRepository = rolePermissionRepository;
		this.roleRepository = roleRepository;
		this.permissionRepository = permissionRepository;
	}

	@Override
	public RolePermission createRolePermission(RolePermissionMin rolePermissionMin) throws ValgoException {
		if (findRolePermissionByRoleIdAndPermissionId(rolePermissionMin.getRoleId(),
				rolePermissionMin.getPermissionId()) != null) {
			throw new ValgoException(Constants.ITEM_ALREADY_EXIST, Constants.ROLE_PERMISSION_ALREADY_EXIST);
		}

		Role role = roleRepository.findById(rolePermissionMin.getRoleId()).orElse(null);
		Permission permission = permissionRepository.findById(rolePermissionMin.getPermissionId()).orElse(null);
		if (role != null && permission != null) {
			RolePermissionId rolePermissionId = new RolePermissionId(role, permission);
			RolePermission rolePermission = new RolePermission(rolePermissionId);
			rolePermission.setCreatedOn(new Date());
			return rolePermissionRepository.save(rolePermission);
		}
		return null;
	}

	@Override
	public RolePermission updateRolePermission(RolePermission rolePermission) {
		rolePermission.setLastUpdateOn(new Date());
		return rolePermissionRepository.save(rolePermission);
	}

	@Override
	public List<RolePermission> getAllRolesPermissions() {
		return new ArrayList<>(rolePermissionRepository.getAllRolesPermissions(Constants.STATE_ACTIVATED,
				Constants.STATE_DEACTIVATED));
	}

	@Override
	public RolePermission deleteRolePermission(
			int roleId,
			int permissionId) throws ValgoException {
		RolePermission rolePermissionToDelete = findRolePermissionByRoleIdAndPermissionId(roleId, permissionId);
		rolePermissionRepository.delete(rolePermissionToDelete);
		return rolePermissionToDelete;
	}

	@Override
	public RolePermission findRolePermissionByRoleIdAndPermissionId(
			int roleId,
			int permissionId) throws ValgoException {
		RolePermissionId rolePermissionId = new RolePermissionId();
		Role role = roleRepository.findById(roleId).orElse(null);
		Permission permission = permissionRepository.findById(permissionId).orElse(null);
		if (role != null && permission != null) {
			rolePermissionId.setRole(role);
			rolePermissionId.setPermission(permission);
		}
		return rolePermissionRepository.findByRolePermissionId(rolePermissionId)
				.orElseThrow(() -> new ValgoException(Constants.ITEM_NOT_FOUND,
						Constants.ROLE_PERMISSION_TO_DELETE_NOT_FOUND));
	}

	@Override
	public StateResponse assignPermissionsToRole(
			int roleId,
			PermissionsIds permissionsListWrapper) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new IllegalStateException(Constants.ROLE_NOT_FOUND_MESSAGE + roleId));

		List<Integer> permissionIdsList = permissionsListWrapper.getPermissionsIdsList();
		List<Permission> permissionsToAssingList = new ArrayList<>();
		List<Permission> rolePermissionList;
		List<Permission> permissionsToMaintainList = new ArrayList<>();

		permissionIdsList.forEach(permissionId -> {
			Permission permission = permissionRepository.findById(permissionId).orElse(null);
			if (permission != null && permission.getStatus() == Constants.STATE_ACTIVATED)
				permissionsToAssingList.add(permission);
		});

		rolePermissionList = this.getAllRolePermissions(role.getRoleId());

		for (Permission permission : permissionsToAssingList) {
			if (!rolePermissionList.contains(permission)) {
				RolePermissionId rolePermissionId = new RolePermissionId(role,
						permission);
				RolePermission rolePermission = new RolePermission(rolePermissionId);
				rolePermissionRepository.save(rolePermission);
			} else {
				permissionsToMaintainList.add(permission);
			}
		}

		rolePermissionList.removeAll(permissionsToMaintainList);

		rolePermissionList.forEach(permissionToDelete -> {
			RolePermissionId rolePermissionId = new RolePermissionId(role, permissionToDelete);
			RolePermission rolePermission = new RolePermission(rolePermissionId);
			rolePermissionRepository.delete(rolePermission);
		});

		return new StateResponse("SUCCEEDED");
	}

	@Override
	public StateResponse removePermissionsToRole(
			int roleId,
			PermissionsIds permissionsListWrapper) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new IllegalStateException(Constants.ROLE_NOT_FOUND_MESSAGE + roleId));

		List<Integer> permissionIdsList = permissionsListWrapper.getPermissionsIdsList();

		permissionIdsList.forEach(permissionId -> {
			Permission permission = permissionRepository.findById(permissionId).orElse(null);
			if (permission != null) {
				RolePermissionId rolePermissionId = new RolePermissionId(role, permission);
				rolePermissionRepository.findByRolePermissionId(rolePermissionId)
						.ifPresent(rolePermissionRepository::delete);
			}
		});
		return new StateResponse("SUCCEEDED");
	}

	@Override
	public List<Permission> getAllRolePermissions(int roleId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new IllegalStateException(Constants.ROLE_NOT_FOUND_MESSAGE + roleId));

		List<RolePermission> rolePermissionList = rolePermissionRepository.findByRolePermissionIdRole(role);

		return rolePermissionList
				.stream()
				.map(RolePermission::getPermission)
				.collect(Collectors.toList());
	}
}
