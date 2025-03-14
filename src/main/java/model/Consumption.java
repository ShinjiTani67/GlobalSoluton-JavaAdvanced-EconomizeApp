package model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_consumo")
@ToString
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int killowatt;

    @Column(nullable = false)
    private LocalDate beginDate;

    @Column(nullable = false)
    private double cost;
}
