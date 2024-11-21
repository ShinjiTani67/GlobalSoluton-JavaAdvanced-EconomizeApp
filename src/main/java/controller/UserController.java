package controller;


import io.swagger.v3.oas.annotations.Operation;
import model.User;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuario", description = "Gerencia os dados do usuario")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}