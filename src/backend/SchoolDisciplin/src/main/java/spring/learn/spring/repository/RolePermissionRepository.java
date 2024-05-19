package spring.learn.spring.repository;

import java.util.List;
import java.util.Optional;

import spring.learn.spring.model.Role;
import spring.learn.spring.model.RolePermission;
import spring.learn.spring.model.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {

	@Query("SELECT ur FROM RolePermission ur WHERE ur.status = :activatedStatus OR ur.status = :deActivatedStatus")
	List<RolePermission> getAllRolesPermissions(@Param("activatedStatus") short roleActivatedStatus,
			@Param("deActivatedStatus") short roleDeActivatedStatus);

	Optional<RolePermission> findByRolePermissionId(RolePermissionId rolePermissionId);

	List<RolePermission> findByRolePermissionIdRole(Role role);
}
