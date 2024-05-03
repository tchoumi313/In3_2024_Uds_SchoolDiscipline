package spring.learn.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.learn.spring.model.Role;
import spring.learn.spring.response.AgGridResponse;
import spring.learn.spring.response.RoleDto;
import spring.learn.spring.service.RoleService;
import spring.learn.spring.util.AuthResponse;
import spring.learn.spring.util.Constants;
import spring.learn.spring.util.ErrorObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/roles")
@Tag(name = "Role-Controller")
public class RoleController {

	@Autowired
	private RoleService rolesService;

	@Operation(description = "This endpoint retrieves a list of all roles in the system and returns them as a JSON array. ", summary = " Fetch AllRoles ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json ", array = @ArraySchema(schema = @Schema(implementation = Role.class))) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@GetMapping
	public ResponseEntity<List<Role>> getAllRole() {
		return new ResponseEntity<>(rolesService.getAllRole(), HttpStatus.OK);
	}

	@Operation(description = " This endpoint retrieves a list of active roles in the system and returns them as a JSON array. ", summary = " Fetch an  ActiveRole ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json ", array = @ArraySchema(schema = @Schema(implementation = Role.class))) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@GetMapping("active")
	public ResponseEntity<List<Role>> getActivesRole() {
		return new ResponseEntity<>(rolesService.getActiveRole(), HttpStatus.OK);
	}

	

	@Operation(description = " This endpoint creates a new role based on the information provided in the request body. It ensures that the role name does not already exist in the system. If successful, it returns the created role as a JSON object. ", summary = " Create a Role ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = RoleDto.class)) }),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@PostMapping("create")
	public ResponseEntity<Object> createRole(@RequestBody RoleDto roleDto) {
		if (rolesService.findByRoleName(roleDto.getRoleName()) != null) {
			return new ResponseEntity<>(new ErrorObject(Constants.ITEM_ALREADY_EXIST, Constants.ROLE_NAME_ALREADY_EXIST,
					Constants.ITEM_ALREADY_EXIST), HttpStatus.BAD_REQUEST);
		}

		Role roleCreated = rolesService.createOrUpdateRole(roleDto);
		RoleDto response = new RoleDto(
				roleCreated.getRoleId(),
				roleCreated.getRoleName(),
				roleCreated.getRoleDesc(),
				roleCreated.getStatus(),
				roleCreated.getCreatedOn(),
				roleCreated.getLastUpdateOn());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(description = " This endpoint updates an existing role based on the information provided in the request body. It ensures that the updated role name is not already used by another role. If successful, it returns the updated role as a JSON object. ", summary = " Update a Role ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = RoleDto.class)) }),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@PutMapping("update")
	public ResponseEntity<Object> updateRole(@RequestBody RoleDto roleDto) {
		Role roleByName = rolesService.findByRoleName(roleDto.getRoleName());
		if (roleByName != null && (!roleByName.getRoleId().equals(roleDto.getRoleId()))) {
			return new ResponseEntity<>(new ErrorObject(Constants.ITEM_ALREADY_EXIST, Constants.ROLE_NAME_ALREADY_USED,
					Constants.ITEM_ALREADY_EXIST), HttpStatus.BAD_REQUEST);
		}

		Role roleUpdated = rolesService.createOrUpdateRole(roleDto);
		RoleDto response = new RoleDto(
				roleUpdated.getRoleId(),
				roleUpdated.getRoleName(),
				roleUpdated.getRoleDesc(),
				roleUpdated.getStatus(),
				roleUpdated.getCreatedOn(),
				roleUpdated.getLastUpdateOn());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(description = " This endpoint deletes a role identified by the roleId. If the role is found and deleted successfully, it returns the deleted role as a JSON object. If the role is not found, it should return an error. ", summary = " Delete a Role by it Id ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = Role.class)) }),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@DeleteMapping("delete/{roleId}")
	public ResponseEntity<Object> deleteRole(@PathVariable("roleId") int roleId) {
		Role roleDeleted = rolesService.deleteRole(roleId);
		if (roleDeleted != null) {
			return new ResponseEntity<>(roleDeleted, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorObject(Constants.ITEM_NOT_FOUND, Constants.ROLE_TO_DELETE_NOT_FOUND,
					Constants.ITEM_NOT_FOUND), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(description = " This endpoint retrieves a role by specifying the roleId and returns the role as a JSON object if found.", summary = " Fetch a Role by it Id ", responses = {
			@ApiResponse(description = "OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = Role.class)) }),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	@GetMapping("getRole/{roleId}")
	public ResponseEntity<Role> findRoleById(@PathVariable("roleId") int roleId) {
		Role roleFound = rolesService.findRoleById(roleId);
		return new ResponseEntity<>(roleFound, HttpStatus.OK);
	}

	@Operation(description = " This endpoint retrieves a list of archived roles in the system and returns them as a JSON array. ", summary = " Fetch all ArchivedRoles ", responses = {
			@ApiResponse(description = " OK ", responseCode = "200 ", content = {
					@Content(mediaType = "application/json ", array = @ArraySchema(schema = @Schema(implementation = Role.class))) }),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))) })

	public ResponseEntity<List<Role>> getAllArchivedRole() {
		return new ResponseEntity<>(rolesService.getAllArchivedRole(), HttpStatus.OK);
	}

	@PostMapping("/records")
	public AgGridResponse records(@RequestParam("page") int page){
		int size = this.getAllRole().getBody().size();
		return rolesService.records(page, size);
	}

}
