package pl.jorgX.database.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceDAO, UUID> {

    List<PlaceDAO> findByCityId(UUID cityId);
    Optional<PlaceDAO> findByName(String name);

    @Query("SELECT COUNT(p) FROM PlaceDAO p")
    int countPlaces();
}
