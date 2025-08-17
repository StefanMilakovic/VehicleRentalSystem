package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.ElectricScooter;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.ElectricScooterService;

import java.util.List;

@RestController
@RequestMapping("/api/electric-scooters")
//@CrossOrigin(origins = "http://localhost:4200")
public class ElectricScooterController {

    private final ElectricScooterService electricScooterService;

    public ElectricScooterController(ElectricScooterService electricScooterService) {
        this.electricScooterService = electricScooterService;
    }

    @GetMapping
    public List<ElectricScooter> getAllScooters() {
        return electricScooterService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectricScooter> getScooterById(@PathVariable Integer id) {
        return electricScooterService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ElectricScooter createScooter(@RequestBody ElectricScooter scooter) {
        scooter.setId(null);
        return electricScooterService.save(scooter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElectricScooter> updateScooter(@PathVariable Integer id, @RequestBody ElectricScooter scooter) {
        if (!electricScooterService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        scooter.setId(id);
        return ResponseEntity.ok(electricScooterService.save(scooter));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable Integer id) {
        if (!electricScooterService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        electricScooterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //za csv

    @PostMapping("/bulk-upload")
    public ResponseEntity<Void> bulkUploadElectricScooters(@RequestBody List<ElectricScooter> scooters) {
        electricScooterService.saveAll(scooters);
        return ResponseEntity.ok().build();
    }
}
