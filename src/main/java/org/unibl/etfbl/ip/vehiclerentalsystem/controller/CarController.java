package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Car;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
//@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id) {
        return carService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        car.setId(null);
        return carService.save(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car car) {
        if (!carService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        car.setId(id);
        Car updatedCar = carService.save(car);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {
        if (!carService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //za csv
    @PostMapping("/bulk-upload")
    public ResponseEntity<Void> bulkUploadCars(@RequestBody List<Car> cars) {
        carService.saveAll(cars);
        return ResponseEntity.ok().build();
    }
}
