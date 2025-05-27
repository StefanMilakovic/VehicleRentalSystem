package org.unibl.etfbl.ip.vehiclerentalsystem.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Client;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Employee;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ClientRepository;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.EmployeeRepository;

import java.util.Optional;

@Service
public class UserLoginService {
    /*

    //jako upitna klasa
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserLoginService(EmployeeRepository employeeRepository,
                            ClientRepository clientRepository,
                            PasswordEncoder passwordEncoder,
                            JwtUtil jwtUtil) {
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        // 1. Pokušaj employee
        Optional<Employee> emp = employeeRepository.findByUsername(request.getUsername());
        if (emp.isPresent() && passwordEncoder.matches(request.getPassword(), emp.get().getPasswordHash())) {
            String token = jwtUtil.generateToken(emp.get().getUsername(), "EMPLOYEE");
            return new AuthenticationResponse(token, "EMPLOYEE");
        }

        // 2. Pokušaj client
        Optional<Client> client = clientRepository.findByUsername(request.getUsername());
        if (client.isPresent() && passwordEncoder.matches(request.getPassword(), client.get().getPassword())) {
            String token = jwtUtil.generateToken(client.get().getUsername(), "CLIENT");
            return new AuthenticationResponse(token, "CLIENT");
        }

        // Ako ništa ne nađeš
        throw new UsernameNotFoundException("Invalid username or password");
    }

     */
}
