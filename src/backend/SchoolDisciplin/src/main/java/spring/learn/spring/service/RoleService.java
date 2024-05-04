package spring.learn.spring.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import spring.learn.spring.model.Role;
import spring.learn.spring.response.AgGridResponse;
import spring.learn.spring.response.RoleDto;


public interface RoleService {

	public Role createOrUpdateRole(RoleDto roleDto);

	public List<Role> getAllRole();

	public List<Role> getActiveRole();

	public Role deleteRole(int roleId);

	public Role findRoleById(int roleId);

	public Role findByRoleName(String roleName);

	public List<Role> getAllArchivedRole();

	public AgGridResponse records(Integer page, Integer size);

	public AgGridResponse searchAll(Specification<Role> specification, Integer page, Integer size);
}
