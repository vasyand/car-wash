package ru.lieague.carwash.model.entity;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Entity
@Getter
@Setter
public class CarWashService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer duration;
    private String description;
    private Double cost;
    @Enumerated(STRING)
    private CarWashServiceType carWashServiceType;

}
