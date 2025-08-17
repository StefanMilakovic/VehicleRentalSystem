package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.RentalPrice;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.RentalPriceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RentalPriceService {

    private final RentalPriceRepository rentalPriceRepository;

    public RentalPriceService(RentalPriceRepository rentalPriceRepository) {
        this.rentalPriceRepository = rentalPriceRepository;
    }

    public List<RentalPrice> findAll() {
        return rentalPriceRepository.findAll();
    }

    public Optional<RentalPrice> findById(Integer id) {
        return rentalPriceRepository.findById(id);
    }

    public Optional<RentalPrice> getByVehicleType(String vehicleType) {
        return rentalPriceRepository.findByVehicleType(vehicleType);
    }

    public RentalPrice updateRentalPrice(String vehicleType, RentalPrice updatedPrice) {
        Optional<RentalPrice> optionalRentalPrice = rentalPriceRepository.findByVehicleType(vehicleType);
        if (optionalRentalPrice.isPresent()) {
            RentalPrice rentalPrice = optionalRentalPrice.get();
            rentalPrice.setPricePerMinute(updatedPrice.getPricePerMinute());
            return rentalPriceRepository.save(rentalPrice);
        } else {
            updatedPrice.setVehicleType(vehicleType);
            return rentalPriceRepository.save(updatedPrice);
        }
    }
}
