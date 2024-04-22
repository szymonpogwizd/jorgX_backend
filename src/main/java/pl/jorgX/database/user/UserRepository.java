package pl.jorgX.database.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, UUID> {

    List<UserDAO> findByUserType(UserType userType);

    Optional<UserDAO> findByEmail(String email);

    Optional<UserDAO> findByName(String name);

    @Query("SELECT u FROM UserDAO u WHERE u.name LIKE %:fragment% OR u.email LIKE %:fragment%")
    List<UserDAO> findByUserNameOrEmail(String fragment);

    @Query("SELECT COUNT(u) FROM UserDAO u")
    int countUsers();

    @Modifying
    @Query(value = "INSERT INTO users (id, active, email, name, password, user_type, default_item) " +
            "VALUES (:id, :active, :email, :name, :password, :userType, :defaultItem) ON CONFLICT DO NOTHING", nativeQuery = true)
    void insertUser(
            @Param("id") UUID id,
            @Param("active") boolean active,
            @Param("email") String email,
            @Param("name") String name,
            @Param("password") String password,
            @Param("userType") String userType,
            @Param("defaultItem") boolean defaultItem
    );
}
