package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Rental;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.RentalService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "http://localhost:4200")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Integer id) {
        Optional<Rental> rental = rentalService.findById(id);
        return rental.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.save(rental);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Integer id, @RequestBody Rental rental) {
        if (!rentalService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rental.setId(id);
        return ResponseEntity.ok(rentalService.save(rental));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Integer id) {
        if (!rentalService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rentalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
