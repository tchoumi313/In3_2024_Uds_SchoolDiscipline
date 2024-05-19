package spring.learn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import spring.learn.spring.model.Role;
import spring.learn.spring.model.UserRole;
import spring.learn.spring.exceptions.ApiException;
import spring.learn.spring.response.RolesIds;
import spring.learn.spring.response.StateResponse;
import spring.learn.spring.response.UserRoleMin;
import spring.learn.spring.service.UsersRolesService;
import spring.learn.spring.util.AuthResponse;
import spring.learn.spring.util.Constants;
import spring.learn.spring.util.ErrorObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users-roles")
public class UsersRolesController {

	@Autowired
	private UsersRolesService usersRoleService;

	@Operation(description = " This endpoint retrieves a list of all user roles in the system and returns them as a JSON array. ", summary = " Fetch AllUserRoles ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json ", array = @ArraySchema(schema = @Schema(implementation = UserRole.class))) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal error server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@GetMapping
	public ResponseEntity<List<UserRole>> getAllUsersRole() {
		return new ResponseEntity<>(usersRoleService.getAllUsersRoles(), HttpStatus.OK);
	}

	@Operation(description = "This endpoint creates a new user role based on the information provided in the request body. It ensures that the user role does not already exist for the specified user and role. If successful, it returns the created user role as a JSON object. ", summary = " Create a UserRole ", responses = {
			@ApiResponse(description = " OK", responseCode = "200 ", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = UserRole.class)) }),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal error server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@PostMapping
	public ResponseEntity<UserRole> createUserRole(@RequestBody UserRoleMin userRole) throws ApiException {
		if (usersRoleService.findUserRoleByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId()) != null) {
			throw new ApiException(Constants.ITEM_ALREADY_EXIST, Constants.USER_ROLE_ALREDY_EXISTS);

		}
		UserRole userRoleCreated = usersRoleService.createUserRole(userRole);
		return new ResponseEntity<>(userRoleCreated, HttpStatus.OK);
	}

	@Operation(description = "This endpoint deletes a user role for the specified user and role IDs. If the user role is found and deleted successfully, it returns the deleted user role as a JSON object. If the user role is not found, it should return an error. ", summary = " Delete a UserRole ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserRole.class)) }),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal error server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@DeleteMapping("{userId}/{roleId}")
	public ResponseEntity<UserRole> deleteRole(@PathVariable("userId") int userId, @PathVariable("roleId") int roleId)
			throws ApiException {
		UserRole userRoleDeleted = usersRoleService.deleteUserRole(userId, roleId);
		if (userRoleDeleted != null) {
			return new ResponseEntity<>(userRoleDeleted, HttpStatus.OK);
		} else {
			throw new ApiException(Constants.ITEM_NOT_FOUND, Constants.USER_ROLE_TO_DELETE_NOT_FOUND);
		}
	}

	@Operation(description = "This endpoint retrieves a user role by specifying the user and role IDs. It returns the user role as a JSON object if found. ", summary = " Fetch a UserRole ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json ", array = @ArraySchema(schema = @Schema(implementation = UserRole.class))) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal error server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@GetMapping("{userId}/{roleId}")
	public ResponseEntity<UserRole> findUserRoleById(@PathVariable("userId") int userId,
			@PathVariable("roleId") int roleId) {
		UserRole userRoleFound = usersRoleService.findUserRoleByUserIdAndRoleId(userId, roleId);
		return new ResponseEntity<>(userRoleFound, HttpStatus.OK);
	}

	@Operation(description = " This endpoint assigns one or more roles to a user based on the provided RolesIds object. It returns a StateResponse object in JSON format to indicate the success or failure of the assignment. ", summary = " Assign a Role to a User ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StateResponse.class)) }),

			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal error server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@PostMapping("assign/{userId}")
	public ResponseEntity<StateResponse> assignRoleToUser(@RequestBody RolesIds rolesListWrapper,
			@PathVariable("userId") int userId) {
		StateResponse state = usersRoleService.assignRolesToUser(userId, rolesListWrapper);
		return new ResponseEntity<>(state, HttpStatus.OK);
	}

	@Operation(description = "This endpoint removes one or more roles from a user based on the provided RolesIds object. It returns a StateResponse object in JSON format to indicate the success or failure of the removal.", summary = " Remove Role from User ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StateResponse.class)) }),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal error server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@PostMapping("remove/{userId}")
	public ResponseEntity<StateResponse> removePermissionsToRole(@PathVariable("userId") int userId,
			@RequestBody RolesIds rolesListWrapper) {
		StateResponse state = usersRoleService.removeRolesToUser(userId, rolesListWrapper);
		return new ResponseEntity<>(state, HttpStatus.OK);
	}

	@Operation(description = " This endpoint retrieves a list of roles associated with a specific user identified by the userId. It returns the user roles as a JSON array. ", summary = " Fetch a user's Roles ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserRole.class))) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal error server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@GetMapping("{userId}")
	public ResponseEntity<List<Role>> getAllUserRole(@PathVariable("userId") Integer userId) {
		List<Role> rolesList = usersRoleService.getAllUserRoles(userId);
		return new ResponseEntity<>(rolesList, HttpStatus.OK);
	}
}
