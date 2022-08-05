package ru.lieague.carwash.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private Double discount;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList;
}
