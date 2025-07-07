package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.dto.EmployeeDTO;
import org.unibl.etfbl.ip.vehiclerentalsystem.dto.EmployeeRegistrationDTO;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Employee;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.EmployeeService;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRegistrationDTO dto) {
        Employee employee = new Employee();
        employee.setUsername(dto.getUsername());
        employee.setPasswordHash(dto.getPassword()); // Pretpostavka: već heširano
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setRole(dto.getRole());

        return ResponseEntity.ok(employeeService.save(employee));
    }

    // READ ALL
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.findById(id);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee updated) {
        Optional<Employee> existing = employeeService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = existing.get();
        employee.setUsername(updated.getUsername());
        employee.setFirstName(updated.getFirstName());
        employee.setLastName(updated.getLastName());
        employee.setRole(updated.getRole());

        // Napomena: Ovdje ne ažuriramo password, osim ako eksplicitno želiš to dozvoliti

        return ResponseEntity.ok(employeeService.save(employee));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        if (employeeService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
