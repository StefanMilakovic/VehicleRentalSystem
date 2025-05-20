package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Car;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // GET /api/cars - vrati sve automobile
    @GetMapping
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    // GET /api/cars/{id} - vrati auto po ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id) {
        return carService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/cars - kreiraj novi auto
    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.save(car);
    }

    // PUT /api/cars/{id} - ažuriraj auto po ID
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car car) {
        if (!carService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        car.setId(id);
        Car updatedCar = carService.save(car);
        return ResponseEntity.ok(updatedCar);
    }

    // DELETE /api/cars/{id} - obriši auto po ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {
        if (!carService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
