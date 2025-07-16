package org.unibl.etfbl.ip.vehiclerentalsystem.security;

import lombok.Data;

@Data
public class AuthRequest
{
    private String username;
    private String password;
}
