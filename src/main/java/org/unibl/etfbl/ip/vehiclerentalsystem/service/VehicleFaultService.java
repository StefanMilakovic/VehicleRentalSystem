package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Vehicle;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.VehicleFault;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.VehicleFaultRepository;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleFaultService {

    private final VehicleFaultRepository vehicleFaultRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleFaultService(VehicleFaultRepository vehicleFaultRepository,  VehicleRepository vehicleRepository) {
        this.vehicleFaultRepository = vehicleFaultRepository;
        this.vehicleRepository = vehicleRepository;

    }

    public List<VehicleFault> findAll() {
        return vehicleFaultRepository.findAll();
    }

    public Optional<VehicleFault> findById(Integer id) {
        return vehicleFaultRepository.findById(id);
    }

    public List<VehicleFault> findByVehicleId(Integer vehicleId) {
        return vehicleFaultRepository.findAllByVehicleId(vehicleId);
    }

    public VehicleFault save(VehicleFault vehicleFault) {
        return vehicleFaultRepository.save(vehicleFault);
    }

    public void deleteById(Integer id) {
        vehicleFaultRepository.deleteById(id);
    }



    //novo, za status vozila
    public void updateVehicleStatusBasedOnFaults(Integer vehicleId) {
        List<VehicleFault> faults = vehicleFaultRepository.findAllByVehicleId(vehicleId);
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (faults.isEmpty()) {
            vehicle.setStatus(Vehicle.VehicleStatus.AVAILABLE);
        } else {
            vehicle.setStatus(Vehicle.VehicleStatus.FAULTY);
        }

        vehicleRepository.save(vehicle);
    }



}
