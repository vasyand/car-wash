package ru.lieague.carwash.config.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailPasswordAuthDto {
    private String email;
    private String password;
}
