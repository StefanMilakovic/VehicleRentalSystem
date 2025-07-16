package org.unibl.etfbl.ip.vehiclerentalsystem.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.unibl.etfbl.ip.vehiclerentalsystem.model.Employee;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String role;
        if (userDetails.getUser() instanceof Employee employee) {
            role = employee.getRole().name().toLowerCase(); // "admin", "operator", "manager"
        } else {
            role = "client";
        }
        String token = jwtUtil.generateToken(userDetails.getUsername(), role);
        return new AuthResponse(token, role);
    }
}
