package ru.lieague.carwash;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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


//	@Bean
//	CommandLineRunner run() {
//		return args -> {
//			LocalDateTime date = LocalDateTime.of(2022, 8, 5, 15,35,0);
//			Box box = boxRepository.getBestBoxAtThisTime(15, date).get();
//			System.out.println(box.getId());
//		};
//	}

}
