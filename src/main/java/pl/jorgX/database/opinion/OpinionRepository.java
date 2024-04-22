package pl.jorgX.database.opinion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OpinionRepository extends JpaRepository<OpinionDAO, UUID> {
    List<OpinionDAO> findByPlaceId(UUID placeId);

    @Query("SELECT COUNT(o) FROM OpinionDAO o")
    int countOpinions();
}
