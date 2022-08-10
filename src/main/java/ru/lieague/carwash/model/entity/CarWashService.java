package ru.lieague.carwash.model.entity;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import static javax.persistence.EnumType.*;

@Entity
@Getter
@Setter
public class CarWashService {

    @Id
    private Long id;
    @Enumerated(STRING)
    private CarWashServiceType carWashServiceType;
    private Integer duration;
    private String description;
    private Double cost;
}
