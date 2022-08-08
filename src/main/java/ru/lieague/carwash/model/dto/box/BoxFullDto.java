package ru.lieague.carwash.model.dto.box;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class BoxFullDto {
    private Long id;
    private String name;
    private Long operatorId;
    private Double coefficient;
    private LocalTime startWorking;
    private LocalTime endWorking;
}
