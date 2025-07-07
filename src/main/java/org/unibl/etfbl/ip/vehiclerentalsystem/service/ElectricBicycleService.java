package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.ElectricBicycle;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Manufacturer;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ElectricBicycleRepository;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ManufacturerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ElectricBicycleService {

    private final ElectricBicycleRepository electricBicycleRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ElectricBicycleService(ElectricBicycleRepository electricBicycleRepository, ManufacturerRepository manufacturerRepository) {
        this.electricBicycleRepository = electricBicycleRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<ElectricBicycle> findAll() {
        return electricBicycleRepository.findAll();
    }

    public Optional<ElectricBicycle> findById(Integer id) {
        return electricBicycleRepository.findById(id);
    }

    public ElectricBicycle save(ElectricBicycle bicycle) {
        return electricBicycleRepository.save(bicycle);
    }

    public void deleteById(Integer id) {
        electricBicycleRepository.deleteById(id);
    }


    //za csv



    public void saveAll(List<ElectricBicycle> bicycles) {
        for (ElectricBicycle bicycle : bicycles) {
            String manufacturerName = bicycle.getManufacturer().getName();

            Manufacturer manufacturer = (Manufacturer) manufacturerRepository.findByName(manufacturerName)
                    .orElseThrow(() -> new RuntimeException("Manufacturer not found: " + manufacturerName));

            bicycle.setManufacturer(manufacturer); // postavi pravi entitet sa ID-em
        }

        electricBicycleRepository.saveAll(bicycles);
    }

}
