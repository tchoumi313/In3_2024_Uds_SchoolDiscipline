package spring.learn.spring.controller;

import java.util.List;

import spring.learn.spring.response.AgGridResponse;
import spring.learn.spring.response.PermissionDto;
import spring.learn.spring.response.PermissionListWrapperDto;
import spring.learn.spring.response.StatusResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.learn.spring.exceptions.ApiException;
import spring.learn.spring.service.PermissionService;
import spring.learn.spring.util.AuthResponse;
import spring.learn.spring.util.ErrorObject;
import spring.learn.spring.util.PermissionMapper;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/permissions")
@Tag(name = "Permissions")
public class PermissionController {

	private PermissionService permissionService;

	public PermissionController(PermissionService permService) {
		this.permissionService = permService;
	}

	@Operation(description = "This endpoint retrieves a list of all permissions in the system and returns them as a JSON array.", summary = "Fetch all permissions", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionListWrapperDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) })
	})
	@GetMapping(value = "getAllPermissions")
	public @ResponseBody ResponseEntity<PermissionListWrapperDto> getAllPermissions() {
		return new ResponseEntity<>(new PermissionListWrapperDto(permissionService.getAllPermission()), HttpStatus.OK);
	}

	@Operation(description = "This endpoint retrieves a list of active permissions in the system and returns them as a JSON array.", summary = "Fetch all active permissions", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionListWrapperDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) })
	})
	@GetMapping("active-permissions")
	public @ResponseBody ResponseEntity<PermissionListWrapperDto> getActivePermissions() {
		return new ResponseEntity<>(new PermissionListWrapperDto(permissionService.getActivePermission()),
				HttpStatus.OK);
	}

	@Operation(description = "This endpoint creates a new permission based on the information provided in the request body. It ensures that the permission name does not already exist in the system. If successful, it returns the created permission as a JSON object, if not it throws an IllegalStateException with the message \"Permission already exists", summary = "Create a new permission", responses = {
			@ApiResponse(description = "Created with success", responseCode = "201", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) })
	})
	@PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PermissionDto> createPermission(
			@RequestBody PermissionDto permissionDto)
			throws ApiException {
		return new ResponseEntity<>(PermissionMapper.map(permissionService.createPermission(permissionDto)),
				HttpStatus.CREATED);
	}

	@Operation(description = "This endpoint updates an existing permission based on the information provided in the request body. It ensures that the updated permission name is not already used by another permission. If successful, it returns the updated permission as a JSON object, if not it throws and IllegalStateException.", summary = "Create a new permission", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) })
	})
	@PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PermissionDto> updatePermission(@RequestBody PermissionDto permissionDTO)
			throws ApiException {
		return new ResponseEntity<>(PermissionMapper.map(permissionService.updatePermission(permissionDTO)),
				HttpStatus.OK);
	}

	@Operation(description = "This endpoint deletes a permission identified by the permissionId. If the permission is found and deleted successfully, it returns the deleted permission as a JSON object. If the permission is not found, it should throw and IllegalStateException", summary = "Delete an existing permission", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) })
	})
	@DeleteMapping("detele/{permissionId}")
	public @ResponseBody ResponseEntity<StatusResponse> deletePermission(@PathVariable("permissionId") int permissionId)
			throws ApiException {
		return new ResponseEntity<>(permissionService.deletePermission(permissionId), HttpStatus.OK);
	}

	@Operation(description = "This endpoint retrieves a permission by specifying the permissionId and returns the permission as a JSON object if found.", summary = "Get an existing permission", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) })
	})
	@GetMapping("{permissionId}")
	public @ResponseBody ResponseEntity<PermissionDto> getSinglePermission(
			@PathVariable("permissionId") int permissionId)
			throws ApiException {
		PermissionDto response = PermissionMapper.map(permissionService.findPermissionById(permissionId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(description = "This endpoint retrieves a list of archived permissions in the system and returns them as a JSON array.", summary = "Fetch all archived permissions", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionListWrapperDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) })
	})
	@GetMapping("archived-permissions")
	public @ResponseBody ResponseEntity<PermissionListWrapperDto> getAllArchivedPermissions() {
		return new ResponseEntity<>(new PermissionListWrapperDto(permissionService.getAllArchivedPermission()),
				HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for updating permission status ", summary = "Activate/deactivate a permission.", responses = {
			@ApiResponse(description = "Permission status updated successfully.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class))),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) })

	})
	@PutMapping(value = "/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusResponse> updatedPermissionStatus(
			@RequestBody PermissionDto permissionDto)
			throws ApiException {
		return new ResponseEntity<>(permissionService.updatedStatus(permissionDto), HttpStatus.OK);
	}

	@PostMapping("/records")
	public AgGridResponse records(@RequestParam("page") int page){
		int size = this.getAllPermissions().getBody().getPermissionDtos().size();
		return permissionService.records(page, size);
	}
}