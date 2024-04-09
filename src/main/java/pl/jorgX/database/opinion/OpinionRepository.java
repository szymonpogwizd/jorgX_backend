package pl.jorgX.database.opinion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OpinionRepository extends JpaRepository<OpinionDao, UUID> {


}
