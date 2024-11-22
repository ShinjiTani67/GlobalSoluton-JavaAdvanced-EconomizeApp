package mapper;

import dto.HouseDto;
import model.House;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HouseMapper {
    HouseDto toDto(House house);
    House toEntity(House houseDto);
}
