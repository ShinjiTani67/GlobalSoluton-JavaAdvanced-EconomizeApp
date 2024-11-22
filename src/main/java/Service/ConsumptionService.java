package Service;


import dto.ConsumptionDto;
import lombok.AllArgsConstructor;
import mapper.ConsumptionMapper;
import org.springframework.stereotype.Service;
import repository.ConsumptionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ConsumptionService {

    private ConsumptionRepository consumptionRepository;
    private ConsumptionMapper consumptionMapper;

    Optional<ConsumptionDto> buscarUUID(UUID uuid) {
        return consumptionRepository.findByUuid(uuid).map(consumptionMapper::toDto);
    }

    public List<ConsumptionDto> listarConsumos() {
        return StreamSupport.stream(consumptionRepository.findAll().spliterator(), false)
                .map(consumptionMapper::toDto)
                .toList();
    }
}
