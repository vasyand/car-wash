package ru.lieague.carwash.model.dto.car_wash_service;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

import javax.validation.constraints.Min;

@Getter
@Setter
public class CarWashServiceUpdateDto {

    @Min(0)
    private Integer duration;

    @Min(0)
    private Double cost;

    private CarWashServiceType carWashServiceType;
    private String description;
}
