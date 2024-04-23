package pl.jorgX.database.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query(value = "INSERT INTO place (id, name, opening_hours, rating, street, city_id) " +
            "VALUES (:id, :name, :opening_hours, :rating, :street, :city_id) ON CONFLICT DO NOTHING", nativeQuery = true)
    void insertPlace(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("opening_hours") String openingHours,
            @Param("rating") Double rating,
            @Param("street") String street,
            @Param("city_id") UUID city_id
    );
}
