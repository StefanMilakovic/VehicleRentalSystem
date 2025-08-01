package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "rental_price")
public class RentalPrice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_price_id", nullable = false)
    private Integer id;

    @Column(name = "vehicle_type", nullable = false, length = 50)
    private String vehicleType;

    @ColumnDefault("0.00")
    @Column(name = "price_per_minute", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerMinute;

    @Override
    public String toString()
    {
        return "RentalPrice{" +
                "id=" + id +
                ", vehicleType='" + vehicleType + '\'' +
                ", pricePerMinute=" + pricePerMinute +
                '}';
    }
}