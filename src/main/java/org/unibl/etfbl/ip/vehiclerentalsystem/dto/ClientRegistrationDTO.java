package org.unibl.etfbl.ip.vehiclerentalsystem.dto;

import lombok.Data;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Client;

@Data
public class ClientRegistrationDTO
{
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Client.Nationality nationality;
    private String idDocumentNumber;
    private String drivingLicenseNumber;
    private String email;
    private String phone;
    private String avatarUrl;
    private boolean blocked;
}
