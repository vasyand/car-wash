package ru.lieague.carwash.model.dto.car_wash_service;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CarWashServiceCreateDto {

    @NotBlank
    @Min(0)
    private Integer duration;

    @NotBlank
    @Min(0)
    private Double cost;

    @NotBlank
    private CarWashServiceType carWashServiceType;

    @NotBlank
    private String description;
}
