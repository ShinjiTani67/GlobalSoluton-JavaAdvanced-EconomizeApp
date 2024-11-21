package Service;


import dto.ConsumptionDto;
import lombok.AllArgsConstructor;
import mapper.ConsumptionMapper;
import model.Consumption;
import org.springframework.stereotype.Service;
import repository.ConsumptionRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConsumptionService {

    private ConsumptionRepository consumptionRepository;
    private ConsumptionMapper consumptionMapper;

    Optional<ConsumptionDto> buscarUUID(UUID uuid) {
        return consumptionRepository.findByUuid(uuid).map(consumptionMapper::toDto);
    }
}
