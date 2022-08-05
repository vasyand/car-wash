package ru.lieague.carwash;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CarWashApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarWashApplication.class, args);
	}


}
