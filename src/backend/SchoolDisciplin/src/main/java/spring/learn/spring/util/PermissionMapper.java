package spring.learn.spring.util;

import spring.learn.spring.model.Permission;
import spring.learn.spring.response.PermissionDto;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public final class PermissionMapper {

    public static PermissionDto map(Permission permission) {

        PermissionDto dto = new PermissionDto();

        dto.setPermissionId(permission.getPermissionId());
        dto.setCreatedOn(permission.getCreatedOn());
        dto.setLastUpdateOn(permission.getLastUpdateOn());
        dto.setPermissionDesc(permission.getPermissionDesc());
        dto.setPermissionName(permission.getPermissionName());
        dto.setRolePermissionList(permission.getRolePermissionList());
        dto.setStatus(permission.getStatus());

        return dto;
    }

    public static List<Permission> map(Page<Permission> permissionPage) {
        List<Permission> dtos = new ArrayList<>();
        for (Permission projet : permissionPage) {
            dtos.add(projet);
        }
        return dtos;
    }
}
