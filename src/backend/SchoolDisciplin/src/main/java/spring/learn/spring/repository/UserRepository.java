package spring.learn.spring.repository;

import java.util.Date;
import java.util.List;

import spring.learn.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
        User findByUsername(String username);

        boolean existsByUsername(String username);

        User findByUserEmail(String userEmail);

        @Query("UPDATE User u SET u.status = :status, u.lastUpdateOn = :lastUpdateOn WHERE u.id = :userId")
        User deleteUser(@Param("status") short userStatus, @Param("lastUpdateOn") Date lastUpdateOn,
                        @Param("userId") int userId);

        @Query("SELECT u FROM User u WHERE u.status = :activateStatus OR u.status = :deActivateStatus")
        List<User> getAllUser(@Param("activateStatus") short userStatus,
                        @Param("deActivateStatus") short userDeActivateStatus);

        @Query("SELECT u FROM User u WHERE u.status = :archiveStatus")
        List<User> getAllArchivedUser(@Param("archiveStatus") short archiveStatus);

        @Query("select p from User p where upper(p.firstName) like concat('%', upper(?1), '%') or upper(p.lastName) like concat('%', upper(?1), '%') ")
        List<User> getByNameOrSurname(String term);

        @Query("select p from User p where upper(p.userEmail) like concat('%', upper(?1), '%')")
        List<User> findByUserContainingIgnoreCase(String term);

        @Query("select p from User p WHERE p.userEmail = :email")
        User findByEmail(@Param("email") String email);

        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.status = :status  WHERE u.id = :id")
        void updateUserStatus(@Param("id") Integer id, @Param("status") short status);

        List<User> findByUserEmailOrEmailListContaining(String defaultEmail, String emailList);

}
