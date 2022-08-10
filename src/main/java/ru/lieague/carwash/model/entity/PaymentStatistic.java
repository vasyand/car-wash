package ru.lieague.carwash.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_statistics")
public class PaymentStatistic {

    @Id
    private Long id;
    private LocalDateTime paymentTime;
    private Double washingCost;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

    @ManyToOne
    @JoinColumn(name = "wash_service_id")
    private CarWashService carWashService;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
