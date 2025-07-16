package org.unibl.etfbl.ip.vehiclerentalsystem.dto;

import lombok.Data;

@Data
public class GeoPoint
{
    private double latitude;
    private double longitude;

    public GeoPoint(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
