package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.AuthenticationResponse;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Response;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.User;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.RegistrationRequestDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services.AuthenticationService;

import io.swagger.annotations.ApiOperation;

@RestController 
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
     @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequestDTO usuario){
        return ResponseEntity.ok(authenticationService.register(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login( @RequestBody User usuario){
        return ResponseEntity.ok(authenticationService.authenticate(usuario));
    }
}
