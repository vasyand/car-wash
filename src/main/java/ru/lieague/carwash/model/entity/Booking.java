package ru.lieague.carwash.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.lieague.carwash.model.BookingStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.*;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime washingStartTime;
    private LocalDateTime washingEndTime;
    private Double cost;

    @Enumerated(STRING)
    private BookingStatus bookingStatus;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_id")
    private Box box;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_wash_service_id")
    private CarWashService carWashService;

}
