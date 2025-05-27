package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.dto.ClientDTO;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Client;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    /*
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // GET /api/clients - vrati sve klijente
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    // GET /api/clients/{id} - vrati klijenta po ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/clients - kreiraj novog klijenta
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    // PUT /api/clients/{id} - ažuriraj klijenta po ID
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        if (!clientService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        client.setId(id);
        Client updatedClient = clientService.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    // DELETE /api/clients/{id} - obriši klijenta po ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        if (!clientService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

     */

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO dto) {
        Client client = new Client();
        client.setUsername(dto.getUsername());
        client.setPasswordHash(dto.getPassword());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setNationality(dto.getNationality());
        client.setIdDocumentNumber(dto.getIdDocumentNumber());
        client.setDrivingLicenseNumber(dto.getDrivingLicenseNumber());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setAvatarUrl(dto.getAvatarUrl());

        return ResponseEntity.ok(clientService.save(client));
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }
}
