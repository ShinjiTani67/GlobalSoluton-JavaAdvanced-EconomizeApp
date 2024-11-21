package model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tb_house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String adress;

    private int member;
}
