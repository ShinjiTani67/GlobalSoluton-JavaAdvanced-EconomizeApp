package repository;

import model.Consumption;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConsumptionRepository extends CrudRepository<Consumption, Integer> {
    Optional<Consumption> findByUuid(UUID uuid);
}
