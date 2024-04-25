package pl.jorgX.database.opinion;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.user.UserDAO;

import java.util.UUID;

@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "opinion")
public class OpinionDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String opinion;

    @Enumerated(EnumType.STRING)
    private OpinionType opinionType;

    @ManyToOne
    @JoinColumn(name = "placeId")
    private PlaceDAO place;

    @ManyToOne
    @JoinColumn(name = "users")
    private UserDAO user;

    public OpinionDAO() {
    }

    public OpinionDAO(UUID id, String opinion, OpinionType opinionType, PlaceDAO place, UserDAO user) {
        this.id = id;
        this.opinion = opinion;
        this.opinionType = opinionType;
        this.place = place;
        this.user = user;
    }
}
