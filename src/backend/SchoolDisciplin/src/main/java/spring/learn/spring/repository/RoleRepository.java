package spring.learn.spring.repository;

import java.util.List;

import spring.learn.spring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

	@Query("SELECT r FROM Role r WHERE r.status = :activatedStatus OR r.status = :deActivatedStatus")
	List<Role> getAllRole(@Param("activatedStatus") short roleActivatedStatus,
			@Param("deActivatedStatus") short roleDeActivatedStatus);

	@Query("SELECT r FROM Role r WHERE r.status = :activatedStatus")
	List<Role> getActiveRole(@Param("activatedStatus") short roleActivatedStatus);

	Role findByRoleName(String roleName);

	@Query("SELECT r FROM Role r WHERE r.status = :archivedStatus")
	List<Role> getAllArchivedRole(@Param("archivedStatus") short archivedStatus);
}
