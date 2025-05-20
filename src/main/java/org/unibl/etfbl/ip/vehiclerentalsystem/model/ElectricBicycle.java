package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "electric_bicycle")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class ElectricBicycle extends Vehicle {

    @Column(name = "autonomy", nullable = false)
    private Integer autonomy;
}
