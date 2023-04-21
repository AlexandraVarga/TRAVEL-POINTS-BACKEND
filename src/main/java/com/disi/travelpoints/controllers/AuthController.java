package com.disi.travelpoints.controllers;

import com.disi.travelpoints.dto.HttpMessageDto;
import com.disi.travelpoints.dto.JwtResponse;
import com.disi.travelpoints.dto.LoginDto;
import com.disi.travelpoints.dto.RegisterDto;
import com.disi.travelpoints.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto registerDto) {
        HttpMessageDto httpMessageDto = userService.register(registerDto);
        return new ResponseEntity<>(httpMessageDto.getErrorMessage(), httpMessageDto.getHttpStatus());
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

}
