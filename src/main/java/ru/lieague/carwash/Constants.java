package ru.lieague.carwash;

import java.time.LocalTime;

public class Constants {
    private Constants (){}
    public static final LocalTime WORKING_DAY_START_HOUR = LocalTime.of(6, 0);
    public static final LocalTime WORKING_DAY_END_HOUR = LocalTime.of(0, 0);

    public final static String BEARER_PARAMETER = "Bearer ";
    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String REFRESH_TOKEN_HEADER = "X-Refresh-Token";
}
