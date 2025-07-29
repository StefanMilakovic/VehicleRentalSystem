package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.RentalPrice;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.RentalPriceService;

import java.util.List;

@RestController
@RequestMapping("/api/rental-prices")
public class RentalPriceController {

    private final RentalPriceService rentalPriceService;

    public RentalPriceController(RentalPriceService rentalPriceService) {
        this.rentalPriceService = rentalPriceService;
    }

    @GetMapping
    public List<RentalPrice> getAll() {
        return rentalPriceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalPrice> getById(@PathVariable Integer id) {
        return rentalPriceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{vehicleType}")
    public ResponseEntity<RentalPrice> getByVehicleType(@PathVariable String vehicleType) {
        return rentalPriceService.getByVehicleType(vehicleType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{vehicleType}")
    public ResponseEntity<RentalPrice> updatePrice(@PathVariable String vehicleType, @RequestBody RentalPrice rentalPrice) {
        RentalPrice updated = rentalPriceService.updateRentalPrice(vehicleType, rentalPrice);
        return ResponseEntity.ok(updated);
    }
}
