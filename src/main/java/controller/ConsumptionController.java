package controller;

import Service.ConsumptionService;
import dto.ConsumptionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mapper.ConsumptionMapper;
import model.Consumption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.ConsumptionRepository;
import java.util.List;


@RestController
@RequestMapping("/api/casa")
@Tag(name = "Consumo", description = "Gerencia os dados do consumo da casa")
public class ConsumptionController {

    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionMapper consumptionMapper;
    private final ConsumptionService consumptionService;


    public ConsumptionController(ConsumptionRepository consumptionRepository, ConsumptionMapper consumptionMapper) {
        this.consumptionRepository = consumptionRepository;
        this.consumptionMapper = consumptionMapper;
        this.consumptionService = new ConsumptionService(consumptionRepository, consumptionMapper);
    }

    @GetMapping
    @Operation(summary = "Listar consumos", description = "Retorna todos os consumos cadastrados")
    public ResponseEntity<List<ConsumptionDto>> listarConsumos() {
        List<ConsumptionDto> consumptionDtos = consumptionService.listarConsumos();
        return ResponseEntity.ok(consumptionDtos);
    }


    @PostMapping
    @Operation( summary = "Cadastrar consumo", description = "Adiciona um novo registro de consumo")
    public ResponseEntity<ConsumptionDto> cadastrarConsumo(@Valid @RequestBody ConsumptionDto consumptionDto) {
        Consumption consumption = consumptionMapper.toEntity(consumptionDto);
        Consumption savedConsumption = consumptionRepository.save(consumption);
        return ResponseEntity.ok(consumptionMapper.toDto(savedConsumption));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consumo por ID", description = "Retorna os dados de um consumo específico")
    public ResponseEntity<ConsumptionDto> buscarConsumoPorId(@PathVariable Long id) {
        return consumptionRepository.findById(id)
                .map(consumptionMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar consumo", description = "Atualiza os dados de um consumo existente")
    public ResponseEntity<ConsumptionDto> atualizarConsumo(@PathVariable Long id, @RequestBody ConsumptionDto consumptionDto) {
        return consumptionRepository.findById(id)
                .map(existingConsumption -> {
                    Consumption updatedConsumption = consumptionMapper.toEntity(consumptionDto);
                    updatedConsumption.setId(existingConsumption.getId());
                    return ResponseEntity.ok(consumptionMapper.toDto(consumptionRepository.save(updatedConsumption)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir consumo", description = "Remove um registro de consumo")
    public ResponseEntity<Void> excluirConsumo(@PathVariable Long id) {
        if (consumptionRepository.existsById(id)) {
            consumptionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
