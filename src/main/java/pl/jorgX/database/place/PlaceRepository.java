package pl.jorgX.database.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<PlaceDAO, UUID> {

    List<PlaceDAO> findByCityId(UUID cityId);
    Optional<PlaceDAO> findByName(String name);
}
