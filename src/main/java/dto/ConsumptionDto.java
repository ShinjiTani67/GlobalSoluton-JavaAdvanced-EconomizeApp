package dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionDto {
    private int id;

    private int killowatt;

    private LocalDate beginDate;

    private double cost;
}
