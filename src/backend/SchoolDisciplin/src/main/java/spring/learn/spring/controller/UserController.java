package spring.learn.spring.controller;

import java.util.List;

import spring.learn.spring.exceptions.ApiException;
import spring.learn.spring.model.User;
import spring.learn.spring.response.AgGridResponse;
import spring.learn.spring.response.StatusResponse;
import spring.learn.spring.response.UserDto;
import spring.learn.spring.response.UserListWrapperDto;
import spring.learn.spring.response.UserPasswordChangeModel;
import spring.learn.spring.service.UserService;
import spring.learn.spring.util.AuthResponse;
import spring.learn.spring.util.Constants;
import spring.learn.spring.util.ErrorObject;
import spring.learn.spring.util.UserMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users")
@Tag(name = "User Controller Management.")
public class UserController {

	@Autowired
	private UserService userService;


	@Operation(description = "This endpoind is using for retrieving a list of activated or deactivated user from the database.", summary = "Get all activated or deactivated users", responses = {
			@ApiResponse(description = "Retrieving user list from the database", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserListWrapperDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))
	})
	@GetMapping(value = "/getAllUser")
	public @ResponseBody ResponseEntity<UserListWrapperDto> getAllUser() {
		List<UserDto> userDtos = UserMapper.mapToDtoList(userService.getAllUser());
		return new ResponseEntity<>(new UserListWrapperDto(userDtos), HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for Updating a existing user.", summary = "Update a user.", responses = {
			@ApiResponse(description = "User created successfully.", responseCode = "201", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))
	})
	@PostMapping(value = "/create")
	public @ResponseBody ResponseEntity<UserDto> createUser(@RequestBody UserDto userdDto) throws ApiException {
		if (userService.findByUserMAil(userdDto.getUserEmail()) != null) {
			throw new ApiException(Constants.ITEM_ALREADY_EXIST, Constants.EMAIL_ALREADY_EXIST);
		} else if (userService.findByUserName(userdDto.getUsername()) != null) {
			throw new ApiException(Constants.ITEM_ALREADY_EXIST, Constants.USERNAME_ALREADY_EXIST);
		}

		UserDto response = UserMapper.map(userService.createOrUpdateUser(userdDto));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(description = "This endpoind is using for Updating User.", summary = "Update a user.", responses = {
			@ApiResponse(description = "User updated successfully.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))
	})
	@PutMapping(value = "/update")
	public @ResponseBody ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) throws ApiException {
		User userByMail = userService.findByUserMAil(userDto.getUserEmail());
		User userByUserName = userService.findByUserName(userDto.getUsername());
		if ((userByMail != null) && (!(userByMail.getId().equals(userDto.getId())))) {
			throw new ApiException(Constants.ITEM_ALREADY_EXIST, Constants.EMAIL_ALREADY_EXIST);
		}
		if ((userByUserName != null) && (!(userByUserName.getId().equals(userDto.getId())))) {
			throw new ApiException(Constants.ITEM_ALREADY_EXIST, Constants.USERNAME_ALREADY_EXIST);
		}
		// userDto.setRoleList(null);
		User userUpdated = userService.createOrUpdateUser(userDto);
		return new ResponseEntity<>(UserMapper.map(userUpdated), HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for deleting User.", summary = "This endpoint delete an existing user.The user most have an ADMIN role or any role related to ADMIN.", responses = {
			@ApiResponse(description = "User deleted successfully.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))
	})
	@DeleteMapping("delete/{userId}")
	public @ResponseBody ResponseEntity<StatusResponse> deleteUser(@PathVariable("userId") int userId)
			throws ApiException {

		return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for retreives user by his id.", summary = "Get user by id", responses = {
			@ApiResponse(description = "User fetched successfully.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))
	})
	@GetMapping(value = "/find-user-by-id/{userId}")
	public ResponseEntity<UserDto> findUserById(@PathVariable("userId") int userId) throws ApiException {
		UserDto response = UserMapper.map(userService.findUserById(userId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for updating user password", summary = "This endpoint update the current user password.The user most have an ADMIN role or any role related to ADMIN.", responses = {
			@ApiResponse(description = "User password updated successfully.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))
	})
	@PutMapping(value = "/update-user-password")
	public @ResponseBody ResponseEntity<UserDto> updateUserPassword(@RequestBody UserPasswordChangeModel user)
			throws ApiException {
		UserDto response = UserMapper.map(userService.updateUserPassword(user.getUserId(), user.getOldPassword(),
				user.getNewPassword()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for a reset user password. An email should be send to a user with the new password.", summary = "Reset a user password.", responses = {
			@ApiResponse(description = "Password reset and email send successfully.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))

	})
	@GetMapping(value = "/reset-password/{userMail}")
	public @ResponseBody ResponseEntity<StatusResponse> resetUserPassword(@PathVariable("userMail") String userMail)
			throws ApiException {

		return new ResponseEntity<>(userService.resetPassword(userMail), HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for fetching all archived user ", summary = "This endpoint is for fetching all archived user", responses = {
			@ApiResponse(description = "Archived user fetched.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserListWrapperDto.class))
			}),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))
	})
	@GetMapping(value = "/get-all-archived-user", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserListWrapperDto> getAllArchivedUser() {

		List<UserDto> userDtos = UserMapper.mapToDtoList(userService.getAllArchivedUser());
		return new ResponseEntity<>(new UserListWrapperDto(userDtos), HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for updating user status ", summary = "Activate/deactivate some user", responses = {
			@ApiResponse(description = "User status updated successfully.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))

	})
	@PostMapping(value = "/update-user-status")
	public ResponseEntity<StatusResponse> updateUserStatus(@RequestBody UserDto userDto) throws ApiException {
		StatusResponse response = userService.updateUserStatus(userDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(description = "This endpoind is using for adding new user email", summary = "This endpoint is for adding new user email adress.", responses = {
			@ApiResponse(description = "User updated successfully.", responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))
			}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = {
					@Content(mediaType = "application/json ", schema = @Schema(implementation = ErrorObject.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "500", description = "internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObject.class)))
	})
	@PostMapping(value = "/addNewEmail", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusResponse> addNewEmail(@RequestBody UserDto userDto,
			@RequestParam(value = "newEmail", required = true) String newEmail) throws ApiException {
		User userFound = userService.findUserById(userDto.getId());
		if (userFound == null) {
			throw new ApiException(Constants.USER_NOT_FOUND_CODE, Constants.USER_NOT_FOUND);
		} else {
			return new ResponseEntity<>(userService.addNewEmail(userFound, newEmail), HttpStatus.OK);
		}
	}

	@PostMapping("/records")
	public AgGridResponse records(@RequestParam("page") int page){
		int size = this.getAllUser().getBody().getUserDtos().size();
		return userService.records(page, size);
	}
}
