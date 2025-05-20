package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "vehicle_fault")
public class VehicleFault
{
    @Id
    @Column(name = "vehicle_fault_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "fault_date_time", nullable = false)
    private Instant faultDateTime;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

}