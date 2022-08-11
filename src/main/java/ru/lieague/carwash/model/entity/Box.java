package ru.lieague.carwash.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "boxes")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double coefficient;
    private LocalTime startWorking;
    private LocalTime endWorking;
    private boolean isWorked;

    @OneToOne
    private User operator;

}
