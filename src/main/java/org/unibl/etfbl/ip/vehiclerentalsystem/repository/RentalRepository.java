package org.unibl.etfbl.ip.vehiclerentalsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Rental;

import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer>
{
    List<Rental> findByVehicleId(Long vehicleId);



}
