package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Car;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Manufacturer;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.CarRepository;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ManufacturerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final ManufacturerRepository manufacturerRepository;

    // Konstruktor za injekciju repozitorijuma
    public CarService(CarRepository carRepository, ManufacturerRepository manufacturerRepository) {
        this.carRepository = carRepository;
        this.manufacturerRepository = manufacturerRepository;
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


    //za csv

    public void saveAll(List<Car> cars) {
        for (Car car : cars) {
            String manufacturerName = car.getManufacturer().getName();

            Manufacturer manufacturer = (Manufacturer) manufacturerRepository.findByName(manufacturerName)
                    .orElseThrow(() -> new RuntimeException("Manufacturer not found: " + manufacturerName));

            car.setManufacturer(manufacturer); // postavi pravi entitet sa ID-em
        }

        carRepository.saveAll(cars);
    }

}
