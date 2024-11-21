package controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/casa")
@Tag(name = "Casa", description = "Gerencia os dados da casa")
public class HouseController {
    
}
