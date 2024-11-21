package model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int killowatt;

    @Column(nullable = false)
    private LocalDate beginDate;

    @Column(nullable = false)
    private double cost;
}
