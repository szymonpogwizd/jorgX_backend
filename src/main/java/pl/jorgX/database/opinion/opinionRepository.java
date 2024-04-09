package pl.jorgX.database.opinion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface opinionRepository extends JpaRepository<opinionDao, UUID> {


}
