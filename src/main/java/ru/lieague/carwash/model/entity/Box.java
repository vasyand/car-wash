package ru.lieague.carwash.model.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "boxes")
public class Box {

    @Id
    private Long id;
    private String name;

    @OneToOne
    private User operator;

    private Double coefficient;

    private Integer startWorking;

    private Integer endWorking;

    @OneToMany(mappedBy = "box")
    List<Booking> bookingList;
}
