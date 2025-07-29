package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "rental")
public class Rental
{
    @Id
    @Column(name = "rental_id", nullable = false)
    private Integer id;

    @Column(name = "vehicle_id", nullable = false)
    private Integer vehicleId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "start_datetime")
    private Instant startDatetime;

    @Column(name = "end_datetime")
    private Instant endDatetime;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "pickup_location", nullable = false, columnDefinition = "point")
    private Point pickupLocation;

    @Column(name = "return_location", columnDefinition = "point")
    private Point returnLocation;
}