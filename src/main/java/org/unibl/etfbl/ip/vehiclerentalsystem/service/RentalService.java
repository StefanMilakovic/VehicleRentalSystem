package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Rental;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.RentalRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> findById(Integer id) {
        return rentalRepository.findById(id);
    }

    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    public void deleteById(Integer id) {
        rentalRepository.deleteById(id);
    }

    public List<Rental> findByVehicleId(Long vehicleId) {
        return rentalRepository.findByVehicleId(vehicleId);
    }

}
