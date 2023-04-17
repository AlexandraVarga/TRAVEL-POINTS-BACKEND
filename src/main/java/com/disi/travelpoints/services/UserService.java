package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.HttpMessageDto;
import com.disi.travelpoints.dto.JwtResponse;
import com.disi.travelpoints.dto.LoginDto;
import com.disi.travelpoints.dto.RegisterDto;
import com.disi.travelpoints.dto.mapper.UserMapper;
import com.disi.travelpoints.model.ClientEntity;
import com.disi.travelpoints.model.UserEntity;
import com.disi.travelpoints.repositories.ClientRepository;
import com.disi.travelpoints.repositories.UserRepository;
import com.disi.travelpoints.security.UserDetailsImpl;
import com.disi.travelpoints.security.jwt.JwtUtils;
import com.disi.travelpoints.utils.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public HttpMessageDto register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new HttpMessageDto("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new HttpMessageDto("Error: Email is already in use!", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = UserMapper.toEntity(registerDto);
        ClientEntity client = UserMapper.clientToEntity(registerDto);
        client.setUser(user);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUserRole(RoleEnum.ROLE_CLIENT);
        userRepository.save(user);
        clientRepository.save(client);
        return new HttpMessageDto("User registered successfully!", HttpStatus.OK);
    }

    public JwtResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return JwtResponse.builder()
                .token(jwtUtils.generateJwtToken(authentication))
                .user(UserMapper.toDto((UserDetailsImpl) authentication.getPrincipal()))
                .build();
    }

}
