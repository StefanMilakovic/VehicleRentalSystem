package org.unibl.etfbl.ip.vehiclerentalsystem.service;

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

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Vrati sve klijente
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // Pronađi klijenta po ID
    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    // Sačuvaj ili ažuriraj klijenta
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    // Obriši klijenta po ID
    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }
}
