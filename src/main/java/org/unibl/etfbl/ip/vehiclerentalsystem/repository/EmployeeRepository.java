package org.unibl.etfbl.ip.vehiclerentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
    Optional<Employee> findByUsername(String username);
}
