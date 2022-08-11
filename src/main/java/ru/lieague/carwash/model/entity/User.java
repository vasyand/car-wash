package ru.lieague.carwash.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.lieague.carwash.model.UserRole;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.EnumType.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private Double discount;
    private boolean isEnabled;

    @Enumerated(STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.JOIN)
    private List<PaymentStatistic> paymentStatistics;
}
