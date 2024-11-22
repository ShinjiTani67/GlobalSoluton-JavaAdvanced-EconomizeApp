package mapper;

import dto.HouseDto;
import jakarta.validation.Valid;
import model.House;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HouseMapper {
    HouseDto toDto(House house);
    House toEntity(@Valid HouseDto houseDto);
}
