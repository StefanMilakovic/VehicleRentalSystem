package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "electric_scooter")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class ElectricScooter extends Vehicle
{
    @Column(name = "max_speed", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxSpeed;
}
