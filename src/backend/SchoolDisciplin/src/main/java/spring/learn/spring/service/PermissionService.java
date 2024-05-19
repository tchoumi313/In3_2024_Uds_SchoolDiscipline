package spring.learn.spring.service;

import java.util.List;

import spring.learn.spring.response.PermissionDto;
import spring.learn.spring.response.StatusResponse;

import org.springframework.data.jpa.domain.Specification;

import spring.learn.spring.exceptions.ApiException;
import spring.learn.spring.model.Permission;
import spring.learn.spring.response.AgGridResponse;


public interface PermissionService {
	public Permission createPermission(PermissionDto permissionDto) throws ApiException;

	public Permission updatePermission(PermissionDto permissionDTO) throws ApiException;

	public List<PermissionDto> getAllPermission();

	public List<PermissionDto> getActivePermission();

	public StatusResponse deletePermission(int permId) throws ApiException;

	public Permission findPermissionById(int permId) throws ApiException;

	public Permission findByPermissionName(String permissionName);

	public List<PermissionDto> getAllArchivedPermission();

	public AgGridResponse records(Integer page, Integer size);

	public AgGridResponse searchAll(Specification<Permission> specification, Integer page, Integer size);

	public StatusResponse updatedStatus(PermissionDto permissionDto) throws ApiException;
}