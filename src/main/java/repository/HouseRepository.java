package repository;

import model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, Integer> {
    Optional<House> findByUuid(UUID uuid);
}