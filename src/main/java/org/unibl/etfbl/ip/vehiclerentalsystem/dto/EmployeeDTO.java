package org.unibl.etfbl.ip.vehiclerentalsystem.dto;

import lombok.Data;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Employee;

@Data
public class EmployeeDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Employee.Role role;
}
