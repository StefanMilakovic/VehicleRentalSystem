package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.ElectricScooter;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ElectricScooterRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ElectricScooterService {

    private final ElectricScooterRepository electricScooterRepository;

    public ElectricScooterService(ElectricScooterRepository electricScooterRepository) {
        this.electricScooterRepository = electricScooterRepository;
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
}
