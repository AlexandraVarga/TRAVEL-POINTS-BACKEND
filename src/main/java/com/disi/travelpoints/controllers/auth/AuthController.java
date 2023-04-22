package com.disi.travelpoints.controllers.auth;

import com.disi.travelpoints.dto.*;
import com.disi.travelpoints.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto registerDto) {
        HttpMessageDto httpMessageDto = userService.register(registerDto);
        return new ResponseEntity<>(httpMessageDto.getErrorMessage(), httpMessageDto.getHttpStatus());
    }

    @PostMapping("/login")
    public UserDto login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

}
