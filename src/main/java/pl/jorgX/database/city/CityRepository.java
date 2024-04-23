package pl.jorgX.database.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityDAO, UUID> {

    Optional<CityDAO> findByName(String name);

    @Query("SELECT COUNT(c) FROM CityDAO c")
    int countCity();


    @Modifying
    @Query(value = "INSERT INTO city (id, name, description) " +
            "VALUES (:id, :name, :description) ON CONFLICT DO NOTHING", nativeQuery = true)
    void insertCity(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description

    );

}
