package spring.learn.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import spring.learn.spring.model.Role;
import spring.learn.spring.repository.RoleRepository;
import spring.learn.spring.response.AgGridResponse;
import spring.learn.spring.response.RoleDto;
import spring.learn.spring.service.RoleService;
import spring.learn.spring.util.Constants;
import spring.learn.spring.util.ObjectMapper;
import spring.learn.spring.util.RoleMapper;
import spring.learn.spring.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolesServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepo;

	private static ObjectMapper<Role> objectMapper = new ObjectMapper<>();

	@Override
	public Role createOrUpdateRole(RoleDto roleDto) {
		if (roleDto.getRoleId() != null) {
			roleDto.setLastUpdateOn(new Date());
		} else {
			roleDto.setLastUpdateOn(new Date());
			roleDto.setCreatedOn(new Date());
		}

		return roleRepo.save(roleDto.getRole());
	}

	@Override
	public List<Role> getAllRole() {
		List<Role> roles = new ArrayList<>();
		roleRepo.getAllRole(Constants.STATE_ACTIVATED, Constants.STATE_DEACTIVATED).forEach(roles::add);
		return roles;
	}

	@Override
	public List<Role> getActiveRole() {
		List<Role> roles = new ArrayList<>();
		roleRepo.getActiveRole(Constants.STATE_ACTIVATED).forEach(roles::add);
		return roles;
	}

	@Override
	public Role deleteRole(int roleId) {
		Role roleToDelete = this.findRoleById(roleId);
		if (roleToDelete != null) {
			roleToDelete.setLastUpdateOn(new Date());
			roleToDelete.setStatus(Constants.STATE_DELETED);
			return roleRepo.save(roleToDelete);
		}
		return null;
	}

	@Override
	public Role findRoleById(int roleId) {
		Optional<Role> opt = roleRepo.findById(roleId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public Role findByRoleName(String roleName) {
		return roleRepo.findByRoleName(roleName);
	}

	@Override
	public List<Role> getAllArchivedRole() {
		List<Role> roles = new ArrayList<>();
		roleRepo.getAllArchivedRole(Constants.STATE_ARCHIVE).forEach(roles::add);
		return roles;
	}

	@Override
	public AgGridResponse records(Integer page, Integer size) {
		Page<Role> roles;

		Pageable pageRequest = PageRequest.of(page, size, Sort.by("roleId"));
		roles = roleRepo.findAll(pageRequest);

		AgGridResponse agGridProjet = new AgGridResponse(roles);

		agGridProjet.setContent(
				Utils.castToObject(RoleMapper.map(roles)));

		return agGridProjet;
	}

	@Override
	public AgGridResponse searchAll(Specification<Role> specification, Integer page, Integer size) {
		if (size < 1) {
			throw new IllegalArgumentException("Page size is less than 1");
		}
		if (page < 0) {
			throw new IllegalArgumentException("Page number is less than 0");
		}
		Pageable pageRequest = PageRequest.of(page, size, Sort.by("roleId"));
		Page<Role> params = roleRepo.findAll(specification, pageRequest);
		AgGridResponse agGridResponse = new AgGridResponse(params);
		agGridResponse.setContent(objectMapper.castToObject(params.getContent()));
		return agGridResponse;
	}

}
