package ru.lieague.carwash;

import java.time.LocalTime;

public class Constants {
    private Constants (){}

    public static final LocalTime WORKING_DAY_START_HOUR = LocalTime.of(6, 0);
    public static final LocalTime WORKING_DAY_END_HOUR = LocalTime.of(0, 0);
}
