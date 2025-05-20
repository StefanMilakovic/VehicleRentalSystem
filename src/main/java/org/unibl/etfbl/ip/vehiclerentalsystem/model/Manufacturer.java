package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "manufacturer")
public class Manufacturer
{
    @Id
    @Column(name = "manufacturer_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "country", nullable = false, length = 45)
    private String country;

    @Column(name = "address", nullable = false, length = 45)
    private String address;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "tax", length = 45)
    private String tax;

    @Column(name = "mail", length = 45)
    private String mail;

}