package Service;


import dto.UserDto;
import lombok.AllArgsConstructor;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    Optional<UserDto> buscarUUID(UUID uuid) { return userRepository.findByUuid(uuid).map(userMapper::toDto);
    }
}
