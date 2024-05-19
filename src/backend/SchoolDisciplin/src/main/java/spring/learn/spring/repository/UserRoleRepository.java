package spring.learn.spring.repository;

import java.util.List;

import spring.learn.spring.model.User;
import spring.learn.spring.model.UserRole;
import spring.learn.spring.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

	@Query("SELECT ur FROM UserRole ur WHERE ur.status = :activatedStatus OR ur.status = :deActivatedStatus")
	List<UserRole> getAllUserRoles(@Param("activatedStatus") short roleActivatedStatus,
			@Param("deActivatedStatus") short roleDeActivatedStatus);

	UserRole findByUserRoleId(UserRoleId userRoleId);

	List<UserRole> findByUserRoleIdUser(User user);

	void deleteByUserRoleIdUser(User user);
}
