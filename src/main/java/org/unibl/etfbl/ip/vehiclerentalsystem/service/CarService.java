package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Car;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;

    // Konstruktor za injekciju repozitorijuma
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Vrati sve automobile
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    // Pronađi auto po ID
    public Optional<Car> findById(Integer id) {
        return carRepository.findById(id);
    }

    // Sačuvaj novi ili ažuriraj postojeći auto
    public Car save(Car car) {
        return carRepository.save(car);
    }

    // Obriši auto po ID
    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }
}
