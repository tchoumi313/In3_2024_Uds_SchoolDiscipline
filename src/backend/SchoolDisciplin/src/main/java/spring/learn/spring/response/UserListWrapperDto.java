package spring.learn.spring.response;

import java.io.Serializable;
import java.util.List;

public class UserListWrapperDto implements Serializable {

    private List<UserDto> userDtos;

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    public UserListWrapperDto() {
    }

    public UserListWrapperDto(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

}
