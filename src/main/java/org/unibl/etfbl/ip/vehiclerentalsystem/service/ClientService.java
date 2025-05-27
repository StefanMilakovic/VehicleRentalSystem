package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Client;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        if (client.getPasswordHash() != null && !client.getPasswordHash().startsWith("$2a$")) {
            client.setPasswordHash(passwordEncoder.encode(client.getPasswordHash()));
        }
        return clientRepository.save(client);
    }

    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }
}
