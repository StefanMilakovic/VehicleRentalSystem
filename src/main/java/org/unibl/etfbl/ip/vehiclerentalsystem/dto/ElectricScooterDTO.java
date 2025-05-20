package org.unibl.etfbl.ip.vehiclerentalsystem.dto;

import java.math.BigDecimal;

public class ElectricScooterDTO extends VehicleDTO {
    private BigDecimal maxSpeed;

    public BigDecimal getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(BigDecimal maxSpeed) { this.maxSpeed = maxSpeed; }
}
