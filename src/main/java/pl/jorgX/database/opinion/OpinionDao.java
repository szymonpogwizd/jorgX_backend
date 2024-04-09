package pl.jorgX.database.opinion;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import pl.jorgX.database.place.PlaceDao;


import java.util.UUID;

@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "opinion")
public class OpinionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String nick;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String opinion;

    @ManyToOne
    @JoinColumn( name = "place_id")
    private PlaceDao place;

    public OpinionDao() {

    }
    public OpinionDao(UUID id, String opinion, String nick, PlaceDao place){
        this.id = id;
        this.opinion = opinion;
        this.nick = nick;
        this.place = place;
    }
}
