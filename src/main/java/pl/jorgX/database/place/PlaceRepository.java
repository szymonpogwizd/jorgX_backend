package pl.jorgX.database.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaceRepository extends JpaRepository<PlaceDao, UUID> {

}
