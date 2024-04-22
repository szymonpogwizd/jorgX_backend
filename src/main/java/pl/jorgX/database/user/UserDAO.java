package pl.jorgX.database.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.jorgX.validator.email.Email;
import pl.jorgX.validator.password.Password;

import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "users")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String name;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    @Email
    private String email;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    @Password
    private String password;

    private Boolean active;

    @Column(columnDefinition = "text")
    private String token;

    private ZonedDateTime tokenExpiration;

    private boolean defaultItem;

    public UserDAO() {
    }

    public UserDAO(UUID id, String name, UserType userType, String email, String password, Boolean active, String token, ZonedDateTime tokenExpiration, boolean defaultItem) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.active = active;
        this.token = token;
        this.tokenExpiration = tokenExpiration;
        this.defaultItem = defaultItem;
    }
}
