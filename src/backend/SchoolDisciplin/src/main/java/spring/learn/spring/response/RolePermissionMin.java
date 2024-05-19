package spring.learn.spring.response;

import lombok.Getter;


@Getter
public class RolePermissionMin {
    private int roleId;
    private int permissionId;

    public RolePermissionMin() {
    }

    public RolePermissionMin(int permissionId, int roleId) {
        this.permissionId = permissionId;
        this.roleId = roleId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
