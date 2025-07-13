package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.VehicleFault;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.VehicleFaultRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleFaultService {

    private final VehicleFaultRepository vehicleFaultRepository;

    public VehicleFaultService(VehicleFaultRepository vehicleFaultRepository) {
        this.vehicleFaultRepository = vehicleFaultRepository;
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
}
