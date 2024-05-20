package pl.jorgX.database.opinion;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.user.UserDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OpinionRepository extends JpaRepository<OpinionDAO, UUID> {

    List<OpinionDAO> findByPlaceId(UUID placeId);

    Optional<OpinionDAO> findByUserAndPlace(UserDAO userDAO, PlaceDAO placeDAO);

    @Query("SELECT COUNT(o) FROM OpinionDAO o")
    int countOpinions();

    @Modifying
    @Transactional
    @Query("DELETE FROM OpinionDAO o WHERE o.place.id = :id")
    void deleteAllWithPlaceId(@Param("id") UUID id);

    @Modifying
    @Query(value = "INSERT INTO opinion (id, opinion, opinion_type, place_id, user_id) " +
            "VALUES (:id, :opinion, :opinionType, :placeId, :userId) ON CONFLICT DO NOTHING", nativeQuery = true)
    void insertOpinion(@Param("id") UUID id,
                       @Param("opinion") String opinion,
                       @Param("opinionType") OpinionType opinionType,
                       @Param("placeId") UUID placeId,
                       @Param("userId") UUID userId
    );
}
