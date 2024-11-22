package controller;


import dto.HouseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mapper.HouseMapper;
import model.House;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.HouseRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/casa")
@Tag(name = "Casa", description = "Gerencia os dados da casa")
public class HouseController {

    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    public HouseController(HouseRepository houseRepository, HouseMapper houseMapper) {

            this.houseRepository = houseRepository;
            this.houseMapper = houseMapper;

    }

    @GetMapping
    @Operation(summary = "Listar casas", description = "Retorna todas as casas cadastradas")
    public List<HouseDto> listarCasas () {
        return houseRepository.findAll().stream()
                .map(houseMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Cadastrar casa", description = "Adiciona uma nova casa ao sistema")
    public ResponseEntity<HouseDto> cadastrarCasa (@RequestBody HouseDto houseDto){
        House house = houseMapper.toEntity(houseDto);
        House savedHouse = houseRepository.save(house);
        return ResponseEntity.ok(houseMapper.toDto(savedHouse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar casa por ID", description = "Retorna os dados de uma casa específica")
    public ResponseEntity<HouseDto> buscarCasaPorId ( @PathVariable int id){
        return houseRepository.findById(id)
                .map(houseMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar casa", description = "Atualiza os dados de uma casa existente")
    public ResponseEntity<HouseDto> atualizarCasa ( @PathVariable int id, @RequestBody HouseDto houseDto){
        return houseRepository.findById(id)
                .map(existingHouse -> {
                    House updatedHouse = houseMapper.toEntity(houseDto);
                    updatedHouse.setId(existingHouse.getId());
                    return ResponseEntity.ok(houseMapper.toDto(houseRepository.save(updatedHouse)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir casa", description = "Remove os dados de uma casa do sistema")
    public ResponseEntity<Void> excluirCasa ( @PathVariable int id){
        if (houseRepository.existsById(id)) {
            houseRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}