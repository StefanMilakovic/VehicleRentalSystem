package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.ElectricScooter;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Manufacturer;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ElectricScooterRepository;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ManufacturerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ElectricScooterService {

    private final ElectricScooterRepository electricScooterRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ElectricScooterService(ElectricScooterRepository electricScooterRepository, ManufacturerRepository manufacturerRepository) {
        this.electricScooterRepository = electricScooterRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<ElectricScooter> findAll() {
        return electricScooterRepository.findAll();
    }

    public Optional<ElectricScooter> findById(Integer id) {
        return electricScooterRepository.findById(id);
    }

    public ElectricScooter save(ElectricScooter scooter) {
        return electricScooterRepository.save(scooter);
    }

    public void deleteById(Integer id) {
        electricScooterRepository.deleteById(id);
    }


    //za csv
    public void saveAll(List<ElectricScooter> scooters) {
        for (ElectricScooter scooter : scooters) {
            String manufacturerName = scooter.getManufacturer().getName();

            Manufacturer manufacturer = (Manufacturer) manufacturerRepository.findByName(manufacturerName)
                    .orElseThrow(() -> new RuntimeException("Manufacturer not found: " + manufacturerName));

            scooter.setManufacturer(manufacturer); // postavi pravi entitet sa ID-em
        }

        electricScooterRepository.saveAll(scooters);
    }
}
