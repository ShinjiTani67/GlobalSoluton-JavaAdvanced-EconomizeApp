package controller;


import dto.HouseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mapper.HouseMapper;
import model.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.HouseRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    @GetMapping("/paged")
    @Operation(summary = "Listar casas com paginação", description = "Retorna as casas de forma paginada")
    public Page<HouseDto> listarCasasPaginadas(Pageable pageable) {
        return houseRepository.findAll(pageable)
                .map(houseMapper::toDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar casa por UUID", description = "Retorna os dados de uma casa específica")
    public ResponseEntity<HouseDto> buscarCasaPorId(@PathVariable Long id) {
        return houseRepository.findById(id)
                .map(houseMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar casa", description = "Atualiza os dados de uma casa existente")
    public ResponseEntity<HouseDto> atualizarCasa(@PathVariable long id, @Valid @RequestBody HouseDto houseDto) {
        return houseRepository.findById(id)
                .map(casaExistente -> {
                    House casaAtualizada = houseMapper.toEntity(houseDto);
                    casaAtualizada.setId(casaExistente.getId());
                    House casaSalva = houseRepository.save(casaAtualizada);
                    return ResponseEntity.ok(houseMapper.toDto(casaSalva));
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir casa", description = "Remove os dados de uma casa do sistema")
    public ResponseEntity<Void> excluirCasa ( @PathVariable Long id){
        if (houseRepository.existsById(id)) {
            houseRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}