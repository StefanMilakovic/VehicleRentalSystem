package org.unibl.etfbl.ip.vehiclerentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.ElectricBicycle;

@Repository
public interface ElectricBicycleRepository extends JpaRepository<ElectricBicycle, Integer>
{
}
