package ru.lieague.carwash.model.entity;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.BookingStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    private Long id;
    private LocalDateTime washingStartTime;
    private LocalDateTime washingEndTime;
    private Double cost;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

    @ManyToOne
    @JoinColumn(name = "wash_service_id")
    private CarWashService carWashService;

}
