package ru.lieague.carwash.model.dto.car_wash_service;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CarWashServiceCreateDto {

    @NotNull
    @Min(0)
    private Integer duration;

    @NotNull
    @Min(0)
    private Double cost;

    @NotNull
    private CarWashServiceType carWashServiceType;

    @NotNull
    private String description;
}
