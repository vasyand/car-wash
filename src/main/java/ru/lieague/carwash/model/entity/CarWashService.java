package ru.lieague.carwash.model.entity;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.CarWashServiceType;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class CarWashService {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private CarWashServiceType carWashServiceType;
    private Integer duration;
    private String description;
    private Double cost;

    @OneToMany(mappedBy = "carWashService")
    List<Booking> bookingList;

}
