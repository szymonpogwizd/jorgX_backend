package pl.jorgX.database.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityDAO, UUID> {

    Optional<CityDAO> findByName(String name);

    @Query("SELECT COUNT(c) FROM CityDAO c")
    int countCity();
}
