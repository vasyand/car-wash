package ru.lieague.carwash.model.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class BoxFilter {
    private String name;
    private Long operatorId;
    private Double coefficientGreaterThan;
    private Double coefficientLessThan;
    private LocalTime workingTime;
}
