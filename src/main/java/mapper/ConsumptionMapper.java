package mapper;

import dto.ConsumptionDto;
import model.Consumption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumptionMapper {
    ConsumptionDto toDto(Consumption consumption);
    Consumption toEntity(ConsumptionDto consumptionDto);
}
