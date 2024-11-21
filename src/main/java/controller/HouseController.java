package controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.HouseRepository;

@RestController
@RequestMapping("/api/casa")
@Tag(name = "Casa", description = "Gerencia os dados da casa")
public class HouseController {

    public HouseController(HouseRepository houseRepository) {

        this.houseRepository = houseRepository;
    }
}