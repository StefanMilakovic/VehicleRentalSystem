package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED) // KLJUČNO
public abstract class Vehicle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id", nullable = false)
    private Integer id;

    @Column(name = "purchase_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "model", nullable = false, length = 45)
    private String model;

    @Column(name = "photo_url")
    private String photoUrl;


    /*

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;


     */


    // Promenjen fetch sa LAZY na EAGER
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;
}