package ru.lieague.carwash;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.repository.BookingRepository;
import ru.lieague.carwash.service.BookingService;
import ru.lieague.carwash.service.impl.BoxServiceImpl;
import ru.lieague.carwash.specification.BookingSpecification;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CarWashApplication {
	private final BoxServiceImpl boxServiceImpl;
	private final BookingService bookingService;

	public static void main(String[] args) {
		SpringApplication.run(CarWashApplication.class, args);
	}


	@Bean
	public CommandLineRunner run() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				CarWashService carWashService = new CarWashService();
				carWashService.setDuration(1);
				List<TimeInterval> all = bookingService.getBookingFreeTimeIntervalsForCarWashServiceOnDay(carWashService, LocalDate.now());
				all.forEach(timeInterval -> System.out.println(timeInterval.getFrom() + " - " + timeInterval.getTo()));
			}
		};
	}
}
