package mapper;


import dto.UserDto;
import jakarta.validation.Valid;
import model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(@Valid UserDto userDto);
}
