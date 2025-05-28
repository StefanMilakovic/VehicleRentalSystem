package org.unibl.etfbl.ip.vehiclerentalsystem.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.User;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ClientRepository;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.EmployeeRepository;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<? extends User> userOpt = employeeRepository.findByUsername(username)
                .map(e -> (User) e)
                .or(() -> clientRepository.findByUsername(username).map(c -> (User) c));

        User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
