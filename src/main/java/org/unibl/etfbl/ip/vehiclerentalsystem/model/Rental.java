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

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

     */

    @Column(name = "vehicle_id", nullable = false)
    private Integer vehicleId;


    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

     */



    // Samo ID umjesto reference na Client
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "start_datetime")
    private Instant startDatetime;

    @Column(name = "end_datetime")
    private Instant endDatetime;

    @Column(name = "duration")
    private Integer duration;

/*
 TODO [Reverse Engineering] create field to map the 'pickup_location' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "pickup_location", columnDefinition = "point not null")
    private Object pickupLocation;
*/
/*
 TODO [Reverse Engineering] create field to map the 'return_location' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "return_location", columnDefinition = "point")
    private Object returnLocation;
*/



    //evo ga pokusaj

    @Column(name = "pickup_location", nullable = false, columnDefinition = "point")
    private Point pickupLocation;

    @Column(name = "return_location", columnDefinition = "point")
    private Point returnLocation;
}