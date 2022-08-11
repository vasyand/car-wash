package ru.lieague.carwash.model.dto.box;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.time.LocalTime;

@Getter
@Setter
public class BoxUpdateDto {
    private String name;

    @Min(0)
    private Long operatorId;

    @Min(0)
    private Double coefficient;

    private LocalTime startWorking;
    private LocalTime endWorking;
}
