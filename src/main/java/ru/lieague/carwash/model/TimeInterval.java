package ru.lieague.carwash.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class TimeInterval {
    private LocalTime from;
    private LocalTime to;
}
