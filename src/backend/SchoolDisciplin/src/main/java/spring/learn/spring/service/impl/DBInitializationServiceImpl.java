package spring.learn.spring.service.impl;

import javax.transaction.Transactional;

import spring.learn.spring.model.*;
import spring.learn.spring.rbac.models.AuthorityModel;
import spring.learn.spring.repository.PermissionRepository;
import spring.learn.spring.repository.RolePermissionRepository;
import spring.learn.spring.repository.RoleRepository;
import spring.learn.spring.repository.UserRepository;
import spring.learn.spring.service.DBInitializationService;
import spring.learn.spring.util.Constants;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Transactional
public class DBInitializationServiceImpl implements DBInitializationService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository rolesRepo;
	@Autowired
	PermissionRepository permissionsRepo;
	@Autowired
	RolePermissionRepository rolesPermissionRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	Role adminRole = new Role();
	Role dbaRole = new Role();
	Role userRole = new Role();

	@Value("${app.admin_username}")
	String adminUserName;
	@Value("${app.admin_password}")
	String adminPassword;

	@Override
	public void initUsers() {
		List<Role> rolesList = new ArrayList<>();
		Role adminRol = rolesRepo.findByRoleName("ADMIN");
		if (adminRol != null) {
			rolesList.add(adminRol);
		}
		User user = new User(passwordEncoder.encode(adminPassword), adminUserName, "vergezkenfack2004@gmail.com",
				"Vergez", "Kenfack",
				new Date());
		if ((userRepo.findAll().isEmpty()) && (!userRepo.existsByUsername(adminUserName))) {
			User userSave = userRepo.save(user);
			if (!rolesList.isEmpty()) {
				rolesList.forEach(role -> {
					UserRoleId userRoleId = new UserRoleId(userSave, role);
					UserRole userRol = new UserRole(userRoleId);
					if (userSave.getUsersRolesList() == null) {
						userSave.setUsersRolesList(new ArrayList<>());
					}
					userSave.getUsersRolesList().add(userRol);
				});
			}
			userRepo.save(userSave);
		}
	}

	@Override
	public void initPermissions() {

		Stream.of(new AuthorityModel(Constants.GET_ALL_USERS,
				"Permission d'obtenir la liste des utilisateurs du système sous format JSON"),
				new AuthorityModel(Constants.CREATE_USER,
						"Permission de créer un nouvel utilisateur"),
				new AuthorityModel(Constants.UPDATE_USER_INFOS,
						"Permission de modifier les informations d'un utilisateur y compris la possibilité d'activer ou de désactiver celui-ci"),
				new AuthorityModel(Constants.DELETE_USER,
						"Permission de supprimer un utilisateur"),
				new AuthorityModel(Constants.SEARCH_USER,
						"Permission de rechercher un utilisateur via la barre de recherche"),
				new AuthorityModel(Constants.UPDATE_USER_STATUS,
						"Permission d'activer ou de désactiver un utilisateur"),
				new AuthorityModel(Constants.FIND_USER,
						"Permission de rechercher un utilisateur par son id sur le backend"),
				new AuthorityModel(Constants.GET_ALL_USERS_ROLES,
						"Permission d'obtenir toutes les entrées de la table users-roles sur le backend"),
				new AuthorityModel(Constants.CREATE_USER_ROLE,
						"Permission d'attribuer un role à un utilisateur"),
				new AuthorityModel(Constants.DELETE_USER_ROLE,
						"Permission de supprimer un rôle de la liste des rôles d'un utilisateur"),
				new AuthorityModel(Constants.FIND_USER_ROLE,
						"Permission de rechercher une entrée de la table users-roles par son id sur le backend"),
				new AuthorityModel(Constants.ASSIGN_ROLES_TO_USER,
						"Permission d'assigner ou de retirer une liste de roles à un utilisateur"),
				new AuthorityModel(Constants.REMOVE_ROLES_TO_USER,
						"Permission de retirer une liste de roles à un utilisateur"),
				new AuthorityModel(Constants.GET_ALL_USER_ROLES,
						"Permission d'obtenir la liste des rôles d'un utilisateur"),
				new AuthorityModel(Constants.CREATE_ROLE,
						"Permission de créer un rôle"),
				new AuthorityModel(Constants.UPDATE_ROLE_INFOS,
						"Permission de mettre les informations d'un rôle à jour"),
				new AuthorityModel(Constants.DELETE_ROLE,
						"Permission de supprimer un rôle"),
				new AuthorityModel(Constants.VIEW_ROLE_PERMISSIONS,
						"Permission de lister les permissions d'un rôle"),
				new AuthorityModel(Constants.UPDATE_ROLE_INFOS,
						"Permission de mettre les informations d'un rôle à jour"),
				new AuthorityModel(Constants.UPDATE_ROLE_PERMISSIONS,
						"Permission d'ajouter ou de retirer des permissions à un rôle"),
				new AuthorityModel(Constants.UPDATE_PERMISSION_INFOS,
						"Permission de mettre à jour une permission"))

				.forEach(auth -> {
					if (!permissionsRepo.existsByPermissionName(auth.getCode())) {
						Permission permission = new Permission(new Date(), auth.getCode(), auth.getDescription());
						permissionsRepo.save(permission);
					}
				});
	}

	@Override
	public void initRoles() {
		String adminRoleName = Constants.ADMIN;
		String userRoleName = Constants.USER;
		String dbaRoleName = Constants.SELLER;
		adminRole = new Role(adminRoleName, "The administrator", new Date());
		userRole = new Role(userRoleName, "The user", new Date());
		dbaRole = new Role(dbaRoleName, "The manager", new Date());

		if (rolesRepo.findByRoleName(adminRoleName) == null)
			adminRole = rolesRepo.save(adminRole);

		if (rolesRepo.findByRoleName(userRoleName) == null)
			userRole = rolesRepo.save(userRole);

		if (rolesRepo.findByRoleName(dbaRoleName) == null)
			dbaRole = rolesRepo.save(dbaRole);

		// Assign permissions to admin
		Stream.of(
				Constants.GET_ALL_USERS,
				Constants.CREATE_USER,
				Constants.UPDATE_USER_INFOS,
				Constants.DELETE_USER,
				Constants.SEARCH_USER,
				Constants.UPDATE_USER_STATUS,
				Constants.FIND_USER,
				Constants.GET_ALL_USERS_ROLES,
				Constants.CREATE_USER_ROLE,
				Constants.DELETE_USER_ROLE,
				Constants.FIND_USER_ROLE,
				Constants.ASSIGN_ROLES_TO_USER,
				Constants.REMOVE_ROLES_TO_USER,
				Constants.GET_ALL_USER_ROLES,
				Constants.CREATE_ROLE,
				Constants.UPDATE_ROLE_INFOS,
				Constants.DELETE_ROLE,
				Constants.VIEW_ROLE_PERMISSIONS,
				Constants.UPDATE_ROLE_INFOS,
				Constants.UPDATE_ROLE_PERMISSIONS,
				Constants.UPDATE_PERMISSION_INFOS)
				.forEach(permissionName -> existingAuthorities(adminRoleName, permissionName));

		// Assign permissions to user
		Stream.of(
				Constants.UPDATE_USER_INFOS)
				.forEach(permissionName -> existingAuthorities(userRoleName, permissionName));

		// Assign permissions to dba
		Stream.of(
				Constants.GET_ALL_USERS,
				Constants.CREATE_USER,
				Constants.UPDATE_USER_INFOS,
				Constants.DELETE_USER,
				Constants.SEARCH_USER,
				Constants.UPDATE_USER_STATUS,
				Constants.FIND_USER,
				Constants.GET_ALL_USERS_ROLES,
				Constants.CREATE_USER_ROLE,
				Constants.DELETE_USER_ROLE,
				Constants.FIND_USER_ROLE,
				Constants.ASSIGN_ROLES_TO_USER,
				Constants.REMOVE_ROLES_TO_USER,
				Constants.GET_ALL_USER_ROLES,
				Constants.CREATE_ROLE,
				Constants.UPDATE_ROLE_INFOS,
				Constants.DELETE_ROLE,
				Constants.VIEW_ROLE_PERMISSIONS,
				Constants.UPDATE_ROLE_INFOS,
				Constants.UPDATE_ROLE_PERMISSIONS,
				Constants.UPDATE_PERMISSION_INFOS)
				.forEach(permissionName -> existingAuthorities(dbaRoleName, permissionName));
	}

	private void existingAuthorities(String roleName, String permissionName) {
		if ((permissionsRepo.findByPermissionName(permissionName) != null)
				&& (rolesRepo.findByRoleName(roleName) != null)) {
			RolePermissionId rolePermissionId = new RolePermissionId(rolesRepo.findByRoleName(roleName),
					permissionsRepo.findByPermissionName(permissionName));
			RolePermission rolePermission = new RolePermission(rolePermissionId);
			Optional<RolePermission> rolePermissionFound = rolesPermissionRepo
					.findById(rolePermissionId);
			if (!rolePermissionFound.isPresent()) {
				rolesPermissionRepo.save(rolePermission);
			}
		}
	}
}
