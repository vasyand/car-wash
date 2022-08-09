package ru.lieague.carwash.model.dto.box;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Getter
@Setter
public class BoxCreateDto {

    @NotBlank
    private String name;

    @NotBlank
    private Long operatorId;

    @NotBlank
    @Min(0)
    private Double coefficient;

    @NotBlank
    private LocalTime startWorking;

    @NotBlank
    private LocalTime endWorking;
}
