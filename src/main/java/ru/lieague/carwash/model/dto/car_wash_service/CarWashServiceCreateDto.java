package ru.lieague.carwash.model.dto.car_wash_service;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

@Getter
@Setter
public class CarWashServiceCreateDto {
    private Integer duration;
    private Double cost;
    private CarWashServiceType carWashServiceType;
    private String description;
}
