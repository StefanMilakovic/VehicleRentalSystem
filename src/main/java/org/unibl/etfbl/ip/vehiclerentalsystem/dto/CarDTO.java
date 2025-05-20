package org.unibl.etfbl.ip.vehiclerentalsystem.dto;

import java.time.LocalDate;

public class CarDTO extends VehicleDTO {
    private LocalDate purchaseDate;
    private String description;

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
