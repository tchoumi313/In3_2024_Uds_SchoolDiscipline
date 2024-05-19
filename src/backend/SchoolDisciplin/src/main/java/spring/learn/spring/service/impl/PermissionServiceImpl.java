package spring.learn.spring.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import spring.learn.spring.exceptions.ApiException;
import spring.learn.spring.model.Permission;
import spring.learn.spring.repository.PermissionRepository;
import spring.learn.spring.response.AgGridResponse;
import spring.learn.spring.response.PermissionDto;
import spring.learn.spring.response.StatusResponse;
import spring.learn.spring.service.PermissionService;
import spring.learn.spring.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

	private PermissionRepository permissionRepository;

	public PermissionServiceImpl(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

	@Override
	public Permission createPermission(PermissionDto permissionDto) throws ApiException {
		if (permissionRepository.findByPermissionName(permissionDto.getPermissionName()) != null) {
			throw new ApiException(Constants.ITEM_ALREADY_EXIST, Constants.PERMISSION_NAME_ALREADY_EXIST);
		}

		permissionDto.setCreatedOn(new Date());

		Permission permission = new Permission(
				permissionDto.getCreatedOn(),
				permissionDto.getPermissionName(),
				permissionDto.getPermissionDesc());

		return permissionRepository.save(permission);
	}

	@Override
	public Permission updatePermission(PermissionDto permissionDTO) throws ApiException {

		Optional<Permission> permissionById = permissionRepository.findById(permissionDTO.getPermissionId());

		Permission permissionByName = permissionRepository.findByPermissionName(permissionDTO.getPermissionName());

		if (!permissionById.isPresent())
			throw new ApiException(Constants.ITEM_NOT_FOUND, Constants.PERMISSION_NOT_FOUND);

		if ((permissionDTO.getPermissionName().equals(permissionById.get().getPermissionName())
				&& !permissionDTO.getPermissionId().equals(permissionById.get().getPermissionId())) ||
				((permissionByName != null)
						&& !(permissionByName.getPermissionId().equals(permissionDTO.getPermissionId())))) {
			throw new ApiException(Constants.ITEM_ALREADY_EXIST, Constants.PERMISSION_NAME_ALREADY_EXIST);
		}

		permissionById.get().setPermissionName(permissionDTO.getPermissionName());
		permissionById.get().setPermissionDesc(permissionDTO.getPermissionDesc());
		permissionById.get().setLastUpdateOn(new Date());
		return permissionRepository.save(permissionById.get());
	}

	@Override
	public List<PermissionDto> getAllPermission() {
		return permissionRepository
				.getAllPermission(Constants.STATE_ACTIVATED, Constants.STATE_DEACTIVATED)
				.stream()
				.map(PermissionMapper::map)
				.collect(Collectors.toList());
	}

	@Override
	public List<PermissionDto> getActivePermission() {
		return permissionRepository
				.getActivePermission(Constants.STATE_ACTIVATED)
				.stream()
				.map(PermissionMapper::map)
				.collect(Collectors.toList());
	}

	@Override
	public StatusResponse deletePermission(int permId) throws ApiException {
		Permission permToDelete = findPermissionById(permId);
		permToDelete.setLastUpdateOn(new Date());
		permToDelete.setStatus(Constants.STATE_DELETED);
		permissionRepository.save(permToDelete);

		return new StatusResponse(true);
	}

	@Override
	public Permission findByPermissionName(String permissionName) {
		return permissionRepository.findByPermissionName(permissionName);
	}

	@Override
	public List<PermissionDto> getAllArchivedPermission() {
		return permissionRepository
				.getAllArchivedPermission(Constants.STATE_ARCHIVE)
				.stream()
				.map(PermissionMapper::map)
				.collect(Collectors.toList());
	}

	@Override
	public AgGridResponse records(Integer page, Integer size) {
		Page<Permission> projects;

		Pageable pageRequest = PageRequest.of(page, size, Sort.by("permissionId"));
		projects = permissionRepository.findAll(pageRequest);

		AgGridResponse agGridProject = new AgGridResponse(projects);

		agGridProject.setContent(
				Utils.castToObject(PermissionMapper.map(projects)));

		return agGridProject;
	}

	@Override
	public AgGridResponse searchAll(Specification<Permission> specification, Integer page, Integer size) {
		if (size < 1) {
			throw new IllegalArgumentException("Page size is less than 1");
		}
		if (page < 0) {
			throw new IllegalArgumentException("Page number is less than 0");
		}
		Pageable pageRequest = PageRequest.of(page, size, Sort.by("permissionId"));
		Page<Permission> params = permissionRepository.findAll(specification, pageRequest);
		AgGridResponse agGridResponse = new AgGridResponse(params);
		agGridResponse.setContent(Utils.castToObject(params.getContent()));
		return agGridResponse;
	}

	@Override
	public StatusResponse updatedStatus(PermissionDto permissionDto) throws ApiException {

		Optional<Permission> opt = permissionRepository.findById(permissionDto.getPermissionId());

		if (!opt.isPresent())
			throw new ApiException(Constants.ITEM_NOT_FOUND, Constants.PERMISSION_NOT_FOUND);

		permissionRepository.updatePermissionStatus(permissionDto.getPermissionId(), permissionDto.getStatus());

		return new StatusResponse(true);
	}

	public Permission findPermissionById(int permId) throws ApiException {
		Optional<Permission> opt = permissionRepository.findById(permId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new ApiException(Constants.ITEM_NOT_FOUND, Constants.PERMISSION_NOT_FOUND);
		}
	}

}