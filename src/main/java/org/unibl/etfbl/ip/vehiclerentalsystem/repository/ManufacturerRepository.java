package org.unibl.etfbl.ip.vehiclerentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Manufacturer;

import java.util.Optional;


@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>
{
    Optional<Object> findByName(String manufacturerName);
}
