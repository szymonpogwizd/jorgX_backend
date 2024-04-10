package pl.jorgX.database.opinion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OpinionRepository extends JpaRepository<OpinionDAO, UUID> {
    List<OpinionDAO> findByPlaceId(UUID placeId);
}
