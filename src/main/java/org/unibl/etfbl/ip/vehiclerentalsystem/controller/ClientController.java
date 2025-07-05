package org.unibl.etfbl.ip.vehiclerentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.dto.ClientDTO;
import org.unibl.etfbl.ip.vehiclerentalsystem.dto.ClientRegistrationDTO;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Client;
import org.unibl.etfbl.ip.vehiclerentalsystem.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientRegistrationDTO dto) {
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

    /*
    staro
    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

     */

    //-----------------------------------------------------------------------------
    // Vrati listu klijenata kao DTO (bez passworda i drugih osetljivih podataka)
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clientsDto = clientService.findAllClientsDTO();
        return ResponseEntity.ok(clientsDto);
    }

    // Endpoint za blokiranje/deblokiranje klijenta
    //@PutMapping("/{id}/blocked")
    @PatchMapping("/{id}/blocked")

    public ResponseEntity<ClientDTO> toggleBlockedStatus(@PathVariable Integer id, @RequestParam boolean blocked) {
        Client updatedClient = clientService.updateBlockedStatus(id, blocked);
        if (updatedClient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientService.convertToDTO(updatedClient));
    }


}