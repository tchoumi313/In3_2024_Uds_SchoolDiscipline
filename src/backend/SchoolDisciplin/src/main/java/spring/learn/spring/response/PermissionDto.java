package spring.learn.spring.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import spring.learn.spring.model.RolePermission;

public class PermissionDto implements Serializable {

    private Integer permissionId;

    public PermissionDto(Integer permissionId, Date createdOn, Date lastUpdateOn, String permissionName,
            String permissionDesc, short status, List<RolePermission> rolePermissionList) {
        this.permissionId = permissionId;
        this.createdOn = createdOn;
        this.lastUpdateOn = lastUpdateOn;
        this.permissionName = permissionName;
        this.permissionDesc = permissionDesc;
        this.status = status;
        this.rolePermissionList = rolePermissionList;
    }

    private Date createdOn;
    private Date lastUpdateOn;
    private String permissionName;
    private String permissionDesc;
    private short status;
    private List<RolePermission> rolePermissionList;

    public PermissionDto() {
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
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

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public List<RolePermission> getRolePermissionList() {
        return rolePermissionList;
    }

    public void setRolePermissionList(List<RolePermission> rolePermissionList) {
        this.rolePermissionList = rolePermissionList;
    }

}
