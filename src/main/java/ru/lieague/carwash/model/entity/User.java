package ru.lieague.carwash.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList;
}
