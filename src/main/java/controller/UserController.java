package controller;


import dto.HouseDto;
import dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import mapper.UserMapper;
import model.House;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuario", description = "Gerencia os dados do usuario")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna todos os usuários cadastrados")
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Adiciona um novo usuário ao sistema")
    public User cadastrarUsuario(@RequestBody User user) {
        return userRepository.save(user);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico")
    public ResponseEntity<UserDto> buscarUsuarioPorId(@PathVariable int id) {
        return userRepository.findById(id)
                .map(userMapper::toDto) 
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UserDto> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return userRepository.findById(Math.toIntExact(id))
                .map(usuarioExistente -> {
                    User usuarioAtualizado = userMapper.toEntity(userDto);
                    usuarioAtualizado.setId(usuarioExistente.getId()); // Garante que o ID não seja alterado
                    User usuarioSalvo = userRepository.save(usuarioAtualizado);
                    return ResponseEntity.ok(userMapper.toDto(usuarioSalvo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário", description = "Remove um usuário do sistema")
    public ResponseEntity<Void> excluirUsuario(@PathVariable int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.notFound().build(); 
    }
}