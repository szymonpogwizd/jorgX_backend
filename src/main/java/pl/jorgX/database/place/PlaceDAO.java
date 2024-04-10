package pl.jorgX.database.place;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.jorgX.database.city.CityDAO;

import java.util.UUID;

@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "place")
public class PlaceDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityDAO city;

    public PlaceDAO() {
    }

    public PlaceDAO(UUID id, String name, CityDAO city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
