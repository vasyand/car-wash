package ru.lieague.carwash.model.dto.car_wash_service;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

@Getter
@Setter
public class CarWashServiceFullDto {
    private Long id;
    private Integer duration;
    private Double cost;
    private CarWashServiceType carWashServiceType;
    private String description;
}
