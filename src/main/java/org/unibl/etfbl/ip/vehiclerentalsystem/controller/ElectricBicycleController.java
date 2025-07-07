package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.ElectricBicycle;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.ElectricBicycleService;

import java.util.List;

@RestController
@RequestMapping("/api/electric-bicycles")
@CrossOrigin(origins = "http://localhost:4200")
public class ElectricBicycleController {

    private final ElectricBicycleService electricBicycleService;

    public ElectricBicycleController(ElectricBicycleService electricBicycleService) {
        this.electricBicycleService = electricBicycleService;
    }

    // GET /api/electric-bicycles - svi električni bicikli
    @GetMapping
    public List<ElectricBicycle> getAll() {
        return electricBicycleService.findAll();
    }

    // GET /api/electric-bicycles/{id} - električni bicikl po ID
    @GetMapping("/{id}")
    public ResponseEntity<ElectricBicycle> getById(@PathVariable Integer id) {
        return electricBicycleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/electric-bicycles - kreiraj novi
    @PostMapping
    public ElectricBicycle create(@RequestBody ElectricBicycle bicycle) {
        bicycle.setId(null);
        return electricBicycleService.save(bicycle);
    }

    // PUT /api/electric-bicycles/{id} - ažuriraj postojeći
    @PutMapping("/{id}")
    public ResponseEntity<ElectricBicycle> update(@PathVariable Integer id, @RequestBody ElectricBicycle bicycle) {
        if (!electricBicycleService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bicycle.setId(id);
        ElectricBicycle updated = electricBicycleService.save(bicycle);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/electric-bicycles/{id} - obriši po ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!electricBicycleService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        electricBicycleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //za csv

    @PostMapping("/bulk-upload")
    public ResponseEntity<Void> bulkUploadElectricBicycles(@RequestBody List<ElectricBicycle> bicycles) {
        electricBicycleService.saveAll(bicycles);
        return ResponseEntity.ok().build();
    }


}
