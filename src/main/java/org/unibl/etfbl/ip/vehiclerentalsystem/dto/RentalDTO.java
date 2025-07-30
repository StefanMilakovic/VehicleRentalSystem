package org.unibl.etfbl.ip.vehiclerentalsystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RentalDTO {
    private Long id;
    private Long vehicleId;
    private Long userId;
    private String startDatetime;
    private String endDatetime;
    private Integer duration;
    private GeoPoint pickupLocation;
    private GeoPoint returnLocation;
    private BigDecimal price;
}


