package mapper;


import dto.UserDto;
import jakarta.persistence.*;
import model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(User UserDto);
}
