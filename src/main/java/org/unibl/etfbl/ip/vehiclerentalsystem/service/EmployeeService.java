package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Employee;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        if (employee.getPasswordHash() != null && !employee.getPasswordHash().startsWith("$2a$")) {
            employee.setPasswordHash(passwordEncoder.encode(employee.getPasswordHash()));
        }
        return employeeRepository.save(employee);
    }

    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }
}
