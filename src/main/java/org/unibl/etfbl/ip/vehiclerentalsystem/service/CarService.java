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

    public CarService(CarRepository carRepository, ManufacturerRepository manufacturerRepository) {
        this.carRepository = carRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(Integer id) {
        return carRepository.findById(id);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }

    //za csv
    public void saveAll(List<Car> cars) {
        for (Car car : cars) {
            String manufacturerName = car.getManufacturer().getName();

            Manufacturer manufacturer = (Manufacturer) manufacturerRepository.findByName(manufacturerName)
                    .orElseThrow(() -> new RuntimeException("Manufacturer not found: " + manufacturerName));

            car.setManufacturer(manufacturer);
        }

        carRepository.saveAll(cars);
    }

}
