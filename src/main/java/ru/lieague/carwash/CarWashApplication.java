package ru.lieague.carwash;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.repository.BoxRepository;
import ru.lieague.carwash.service.BoxService;

import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
public class CarWashApplication {
	private final BoxRepository boxRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarWashApplication.class, args);
	}



}
