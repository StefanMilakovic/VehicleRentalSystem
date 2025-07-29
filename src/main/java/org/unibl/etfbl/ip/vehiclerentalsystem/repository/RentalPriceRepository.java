package org.unibl.etfbl.ip.vehiclerentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.RentalPrice;

import java.util.Optional;

@Repository
public interface RentalPriceRepository extends JpaRepository<RentalPrice, Integer> {
    Optional<RentalPrice> findByVehicleType(String vehicleType);
}
