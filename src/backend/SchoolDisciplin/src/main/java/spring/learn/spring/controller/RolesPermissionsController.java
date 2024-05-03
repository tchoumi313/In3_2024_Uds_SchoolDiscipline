package spring.learn.spring.controller;

import spring.learn.spring.response.StateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import spring.learn.spring.exceptions.ValgoException;
import spring.learn.spring.model.RolePermission;
import spring.learn.spring.response.PermissionListWrapperDto;
import spring.learn.spring.response.PermissionsIds;
import spring.learn.spring.response.RolePermissionMin;
import spring.learn.spring.service.RolesPermissionsService;
import spring.learn.spring.util.AuthResponse;
import spring.learn.spring.util.ErrorObject;
import spring.learn.spring.util.PermissionMapper;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/roles-permissions")
@Tag(name = "Roles and Permissions")
public class RolesPermissionsController {

	private final RolesPermissionsService rolesPermissionsService;

	public RolesPermissionsController(RolesPermissionsService rolesPermissionsService) {
		this.rolesPermissionsService = rolesPermissionsService;
	}

	@Operation(description = "This endpoint retrieves a list of all role permissions in the system and returns them as a JSON array.", summary = "Fetch all Roles with their Permissions", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RolePermission.class)))
			}),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403", content = @Content)
	})
	@GetMapping
	public ResponseEntity<List<RolePermission>> getAllRolesWithPermissions() {
		return new ResponseEntity<>(rolesPermissionsService.getAllRolesPermissions(), HttpStatus.OK);
	}

	@Operation(description = "This endpoint creates a new role permission based on the information provided in the request body. It ensures that the role permission does not already exist for the specified role and permission. If successful, it returns the created role permission as a JSON object.", summary = "Create a Specified Role Permission", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RolePermission.class))
			}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403", content = @Content)
	})
	@PostMapping
	public ResponseEntity<RolePermission> createRolePermission(@RequestBody RolePermissionMin rolePermission)
			throws ValgoException {
		return new ResponseEntity<>(rolesPermissionsService.createRolePermission(rolePermission), HttpStatus.CREATED);
	}

	@Operation(description = "This endpoint deletes a role permission for the specified role and permission IDs. If the role permission is found and deleted successfully, it returns the deleted role permission as a JSON object. If the role permission is not found, it should return an error.", summary = "Delete a Specified Role Permission", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RolePermission.class))
			}),
			@ApiResponse(description = "Role's Permission Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403", content = @Content)
	})
	@DeleteMapping("{roleId}/{permissionId}")
	public ResponseEntity<RolePermission> deleteRole(
			@PathVariable("roleId") int roleId,
			@PathVariable("permissionId") int permissionId) throws ValgoException {
		return new ResponseEntity<>(rolesPermissionsService.deleteRolePermission(roleId, permissionId), HttpStatus.OK);
	}

	@Operation(description = "This endpoint retrieves a role permission by specifying the role and permission IDs. It returns the role permission as a JSON object if found.", summary = "Get a Specified Role Permissions", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RolePermission.class))
			}),
			@ApiResponse(description = "Role's Permission Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403", content = @Content)
	})
	@GetMapping("{roleId}/{permissionId}")
	public ResponseEntity<RolePermission> getRolePermission(
			@PathVariable("roleId") int roleId,
			@PathVariable("permissionId") int permissionId) throws ValgoException {
		RolePermission rolePermissionFound = rolesPermissionsService.findRolePermissionByRoleIdAndPermissionId(roleId,
				permissionId);
		return new ResponseEntity<>(rolePermissionFound, HttpStatus.OK);
	}

	@Operation(description = "This endpoint assigns one or more permissions to a role based on the provided PermissionsIds object. It returns a StateResponse object in JSON format to indicate the success or failure of the assignment.", summary = "Assign a Permission to a Specified Role", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StateResponse.class))
			}),
			@ApiResponse(description = "Role Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403", content = @Content)
	})
	@PostMapping("assign-permissions-to-role/{roleId}")
	public ResponseEntity<StateResponse> assignPermissionsToRole(@RequestBody PermissionsIds permissionsListWrapper,
			@PathVariable("roleId") int roleId) {
		return new ResponseEntity<>(rolesPermissionsService.assignPermissionsToRole(roleId, permissionsListWrapper),
				HttpStatus.OK);
	}

	@Operation(description = "This endpoint removes one or more permissions from a role based on the provided PermissionsIds object. It returns a StateResponse object in JSON format to indicate the success or failure of the removal.", summary = "Revoke a Permission from a Specified Role", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StateResponse.class))
			}),
			@ApiResponse(description = "Role Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403", content = @Content)
	})
	@PostMapping("revoke-permissions-from-role/{roleId}")
	public ResponseEntity<StateResponse> revokePermissionsFromRole(
			@PathVariable("roleId") int roleId,
			@RequestBody PermissionsIds permissionsListWrapper) {
		return new ResponseEntity<>(rolesPermissionsService.removePermissionsToRole(roleId, permissionsListWrapper),
				HttpStatus.OK);
	}

	@Operation(description = "This endpoint retrieves a list of permissions associated with a specific role identified by the roleId. It returns the permissions as a JSON array.", summary = "Get all Permissions of a Role", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionListWrapperDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@GetMapping(value = "{roleId}", produces = "application/json")
	public @ResponseBody ResponseEntity<PermissionListWrapperDto> getAllRolePermissions(
			@PathVariable("roleId") Integer roleId) {

		PermissionListWrapperDto permissionListWrapperDto = new PermissionListWrapperDto(
				rolesPermissionsService.getAllRolePermissions(roleId)
						.stream()
						.map(PermissionMapper::map)
						.collect(Collectors.toList()));

		return new ResponseEntity<>(permissionListWrapperDto, HttpStatus.OK);
	}

}
