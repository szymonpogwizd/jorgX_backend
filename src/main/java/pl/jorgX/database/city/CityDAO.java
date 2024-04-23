package pl.jorgX.database.city;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "city")
public class CityDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String description;

    public CityDAO() {
    }

    public CityDAO(UUID id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
