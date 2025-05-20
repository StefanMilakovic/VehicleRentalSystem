package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.ElectricBicycle;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ElectricBicycleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ElectricBicycleService {

    private final ElectricBicycleRepository electricBicycleRepository;

    public ElectricBicycleService(ElectricBicycleRepository electricBicycleRepository) {
        this.electricBicycleRepository = electricBicycleRepository;
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
}
