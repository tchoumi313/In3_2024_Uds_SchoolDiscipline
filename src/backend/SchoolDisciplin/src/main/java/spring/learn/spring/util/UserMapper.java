package spring.learn.spring.util;

import spring.learn.spring.model.User;
import spring.learn.spring.response.UserDto;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public final class UserMapper {

    public static UserDto map(User user) {

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setComment(user.getComment());
        dto.setCreatedOn(user.getCreatedOn());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setLastUpdateOn(user.getLastUpdateOn());
        dto.setPassword(user.getPassword());
        dto.setPermissionList(user.getUserPermissionList());
        dto.setRoleList(user.getUserRoleList());
        dto.setStatus(user.getStatus());
        dto.setUserEmail(user.getUserEmail());
        dto.setUsername(user.getUsername());
        dto.setEmailList(user.getEmailList());

        return dto;
    }

    public static List<User> map(Page<User> userPage) {
        List<User> dtos = new ArrayList<>();
        for (User user : userPage) {
            dtos.add(user);
        }
        return dtos;
    }

    public static List<UserDto> mapToDtoList(Page<User> userPage) {
        List<UserDto> dtos = new ArrayList<>();
        for (User user : userPage) {
            dtos.add(map(user));
        }
        return dtos;
    }

    public static List<UserDto> mapToDtoList(List<User> usersList) {
        List<UserDto> dtos = new ArrayList<>();
        for (User user : usersList) {
            dtos.add(map(user));
        }
        return dtos;
    }
}
