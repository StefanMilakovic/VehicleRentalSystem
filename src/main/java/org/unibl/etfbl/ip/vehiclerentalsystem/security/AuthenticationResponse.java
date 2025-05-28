package org.unibl.etfbl.ip.vehiclerentalsystem.security;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private String role;

    public AuthenticationResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }
}