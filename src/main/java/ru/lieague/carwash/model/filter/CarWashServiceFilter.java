package ru.lieague.carwash.model.filter;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

@Getter
@Setter
public class CarWashServiceFilter {
    private CarWashServiceType carWashServiceType;
    private Integer durationGreaterThan;
    private Integer durationLessThan;
    private String description;
    private Double costGreaterThan;
    private Double costLessThan;
}
