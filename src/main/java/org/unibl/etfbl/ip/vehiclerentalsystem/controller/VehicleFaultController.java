package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.VehicleFault;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.VehicleFaultService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-faults")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleFaultController {

    private final VehicleFaultService vehicleFaultService;

    public VehicleFaultController(VehicleFaultService vehicleFaultService) {
        this.vehicleFaultService = vehicleFaultService;
    }

    @GetMapping
    public List<VehicleFault> getAll() {
        return vehicleFaultService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleFault> getById(@PathVariable Integer id) {
        return vehicleFaultService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<VehicleFault> getFaultsByVehicleId(@PathVariable Integer vehicleId) {
        return vehicleFaultService.findByVehicleId(vehicleId);
    }

    @PostMapping
    public VehicleFault create(@RequestBody VehicleFault vehicleFault) {
        vehicleFault.setId(null);
        return vehicleFaultService.save(vehicleFault);

    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleFault> update(@PathVariable Integer id, @RequestBody VehicleFault vehicleFault) {
        if (!vehicleFaultService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        vehicleFault.setId(id);
        return ResponseEntity.ok(vehicleFaultService.save(vehicleFault));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!vehicleFaultService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        vehicleFaultService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
