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

    @Query("SELECT o.place FROM OpinionDAO o WHERE o.id = :opinionId")
    Optional<PlaceDAO> findByOpinionId(@Param("opinionId") UUID opinionId);

    @Query("SELECT COUNT(p) FROM PlaceDAO p")
    int countPlaces();

    @Modifying
    @Query("UPDATE PlaceDAO p SET p.rating = :rating WHERE p.id = :id")
    void updatePlaceRating(@Param("id") UUID id, @Param("rating") Double rating);

    @Modifying
    @Query(value = "INSERT INTO place (id, name, street, opening_hours, rating, city_Id) " +
            "VALUES (:id, :name, :street, :openingHours, :rating, :cityId) ON CONFLICT DO NOTHING", nativeQuery = true)
    void insertPlace(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("street") String street,
            @Param("openingHours") String openingHours,
            @Param("rating") Double rating,
            @Param("cityId") UUID cityId
    );
}
