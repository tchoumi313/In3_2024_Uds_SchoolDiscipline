package spring.learn.spring.response;

import java.io.Serializable;
import java.util.List;

public class PermissionListWrapperDto implements Serializable {

    private List<PermissionDto> permissionDtos;

    public List<PermissionDto> getPermissionDtos() {
        return permissionDtos;
    }

    public void setPermissionDtos(List<PermissionDto> permissionDtos) {
        this.permissionDtos = permissionDtos;
    }

    public PermissionListWrapperDto() {
    }

    public PermissionListWrapperDto(List<PermissionDto> permissionDtos) {
        this.permissionDtos = permissionDtos;
    }

}
