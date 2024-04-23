package pl.jorgX.database.opinion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OpinionRepository extends JpaRepository<OpinionDAO, UUID> {
    List<OpinionDAO> findByPlaceId(UUID placeId);

    @Query("SELECT COUNT(o) FROM OpinionDAO o")
    int countOpinions();

    @Modifying
    @Query(value = "INSERT INTO opinion (id, nick, opinion, place_id, user_id) " +
            "VALUES (:id, :nick, :opinion, :place_id, :user_id) ON CONFLICT DO NOTHING", nativeQuery = true)
    void insertOpinion(
            @Param("id") UUID id,
            @Param("nick") String nick,
            @Param("opinion") String opinion,
            @Param("place_id") UUID place_id,
            @Param("user_id") UUID user_id
    );
}
