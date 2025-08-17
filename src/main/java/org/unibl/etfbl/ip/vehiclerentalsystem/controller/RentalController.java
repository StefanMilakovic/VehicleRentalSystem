package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.dto.GeoPoint;
import org.unibl.etfbl.ip.vehiclerentalsystem.dto.RentalDTO;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Rental;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.RentalService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rentals")
//@CrossOrigin(origins = "http://localhost:4200")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentalDTO> getAllRentals() {
        return rentalService.findAll().stream().map(rental -> {
            GeoPoint pickup = new GeoPoint(
                    rental.getPickupLocation().getY(), // latitude
                    rental.getPickupLocation().getX()  // longitude
            );

            GeoPoint ret = null;
            if (rental.getReturnLocation() != null) {
                ret = new GeoPoint(
                        rental.getReturnLocation().getY(),
                        rental.getReturnLocation().getX()
                );
            }

            RentalDTO dto = new RentalDTO();
            dto.setId(rental.getId().longValue());
            dto.setVehicleId(rental.getVehicleId().longValue());
            dto.setUserId(rental.getUserId().longValue());
            dto.setStartDatetime(rental.getStartDatetime() != null ? rental.getStartDatetime().toString() : null);
            dto.setEndDatetime(rental.getEndDatetime() != null ? rental.getEndDatetime().toString() : null);
            dto.setDuration(rental.getDuration());
            dto.setPickupLocation(pickup);
            dto.setReturnLocation(ret);
            dto.setPrice(rental.getPrice());

            return dto;
        }).collect(Collectors.toList());
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


    @GetMapping("/vehicle/{vehicleId}")
    public List<RentalDTO> getRentalsByVehicle(@PathVariable Long vehicleId) {
        return rentalService.findByVehicleId(vehicleId).stream().map(rental -> {
            GeoPoint pickup = new GeoPoint(
                    rental.getPickupLocation().getY(), // latitude
                    rental.getPickupLocation().getX()  // longitude
            );

            GeoPoint ret = null;
            if (rental.getReturnLocation() != null) {
                ret = new GeoPoint(
                        rental.getReturnLocation().getY(),
                        rental.getReturnLocation().getX()
                );
            }

            RentalDTO dto = new RentalDTO();
            dto.setId(rental.getId().longValue());
            dto.setVehicleId(rental.getVehicleId().longValue());
            dto.setUserId(rental.getUserId().longValue());
            dto.setStartDatetime(rental.getStartDatetime() != null ? rental.getStartDatetime().toString() : null);
            dto.setEndDatetime(rental.getEndDatetime() != null ? rental.getEndDatetime().toString() : null);
            dto.setDuration(rental.getDuration());
            dto.setPickupLocation(pickup);
            dto.setReturnLocation(ret);
            dto.setPrice(rental.getPrice());

            return dto;
        }).collect(Collectors.toList());
    }



}
