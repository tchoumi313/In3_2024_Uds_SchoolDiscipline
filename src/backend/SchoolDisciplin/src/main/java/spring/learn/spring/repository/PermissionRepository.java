package spring.learn.spring.repository;

import java.util.List;

import spring.learn.spring.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

	boolean existsByPermissionName(String permissionName);

	@Query("SELECT p FROM Permission p WHERE p.status = :activatedStatus OR p.status = :deActivatedStatus")
	List<Permission> getAllPermission(@Param("activatedStatus") short activatedStatus,
			@Param("deActivatedStatus") short deActivatedStatus);

	@Query("SELECT p FROM Permission p WHERE p.status = :activatedStatus")
	List<Permission> getActivePermission(@Param("activatedStatus") short activatedStatus);

	Permission findByPermissionName(String permissionName);

	@Query("SELECT p FROM Permission p WHERE p.status = :archivedStatus")
	List<Permission> getAllArchivedPermission(@Param("archivedStatus") short archivedStatus);

	@Transactional
	@Modifying
	@Query("UPDATE Permission p SET p.status = :status  WHERE p.permissionId = :permissionId")
	void updatePermissionStatus(@Param("permissionId") Integer permissionId, @Param("status") short status);
}
