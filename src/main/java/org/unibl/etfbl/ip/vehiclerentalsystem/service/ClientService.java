package org.unibl.etfbl.ip.vehiclerentalsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etfbl.ip.vehiclerentalsystem.dto.ClientDTO;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Client;
import org.unibl.etfbl.ip.vehiclerentalsystem.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    //-----------------------------------------------------------------------------------------
    public Client updateBlockedStatus(Integer id, boolean blocked) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setBlocked(blocked);
            return clientRepository.save(client);
        }
        return null;
    }

    // DTO metode

    public List<ClientDTO> findAllClientsDTO() {
        return findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setUsername(client.getUsername());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setNationality(client.getNationality());
        dto.setIdDocumentNumber(client.getIdDocumentNumber());
        dto.setDrivingLicenseNumber(client.getDrivingLicenseNumber());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setAvatarUrl(client.getAvatarUrl());
        dto.setBlocked(client.isBlocked());
        return dto;
    }
}