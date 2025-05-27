package org.unibl.etfbl.ip.vehiclerentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>
{
    Optional<Client> findByUsername(String username);
}
