package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "car")
@PrimaryKeyJoinColumn(name = "vehicle_id") // obavezno, jer je PK i FK
public class Car extends Vehicle
{
    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Lob
    @Column(name = "description")
    private String description;
}