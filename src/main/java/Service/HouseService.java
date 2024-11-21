package Service;

import dto.HouseDto;
import lombok.AllArgsConstructor;
import mapper.HouseMapper;
import org.springframework.stereotype.Service;
import repository.HouseRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HouseService {
    private HouseRepository houseRepository;
    private HouseMapper houseMapper;

    Optional<HouseDto>buscarUUID(UUID uuid) { return houseRepository.findByUuid(uuid).map(houseMapper::toDto);
    }
}