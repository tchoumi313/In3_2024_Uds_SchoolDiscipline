package spring.learn.spring.response;

import java.util.Date;
import java.util.List;

import spring.learn.spring.model.Role;
import spring.learn.spring.model.RolePermission;
import spring.learn.spring.model.UserRole;

public class RoleDto {
    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private short status;
    private Date createdOn;
    private Date lastUpdateOn;
    private List<RolePermission> rolePermissionList;
    private List<UserRole> userRoleList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    public List<RolePermission> getRolePermissionList() {
        return rolePermissionList;
    }

    public void setRolePermissionList(List<RolePermission> rolePermissionList) {
        this.rolePermissionList = rolePermissionList;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public RoleDto(Integer roleId, String roleName, String roleDesc, short status, Date createdOn, Date lastUpdateOn) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.status = status;
        this.createdOn = createdOn;
        this.lastUpdateOn = lastUpdateOn;
    }

    public RoleDto() {
    }

    public Role getRole() {
        return new Role(
                this.getRoleId(),
                this.getRoleName(),
                this.getRoleDesc(),
                this.getCreatedOn(),
                this.getLastUpdateOn());
    }

}
