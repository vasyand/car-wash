package ru.lieague.carwash.model.dto.box;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
public class BoxCreateDto {

    @NotNull
    private String name;

    @NotNull
    private Long operatorId;

    @NotNull
    @Min(0)
    private Double coefficient;

    @NotNull
    private LocalTime startWorking;

    @NotNull
    private LocalTime endWorking;
}
