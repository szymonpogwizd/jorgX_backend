package pl.jorgX.database.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface cityRepository extends JpaRepository<CityDao, UUID> {


}