package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.*;
import com.disi.travelpoints.dto.mapper.UserMapper;
import com.disi.travelpoints.model.ClientEntity;
import com.disi.travelpoints.model.UserEntity;
import com.disi.travelpoints.repositories.ClientRepository;
import com.disi.travelpoints.repositories.UserRepository;
import com.disi.travelpoints.security.UserDetailsImpl;
import com.disi.travelpoints.security.jwt.JwtUtils;
import com.disi.travelpoints.utils.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUserRole(RoleEnum.ROLE_CLIENT);
        ClientEntity client = UserMapper.clientToEntity(registerDto);
        client.setUser(user);
        clientRepository.save(client);
        userRepository.save(user);
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

    public UserPasswordDto changePassword(UserPasswordDto userPasswordDto, int userId) {
        //check if user is logged in
        UserEntity userFound = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + "doesn't exist"));

        String currentPassword = userPasswordDto.getCurrentPassword();
        UserEntity updatedUser = UserMapper.passwordDtoToEntity(userPasswordDto);

        if (userFound.getPassword().equals(currentPassword)) {
            userFound.setPassword(updatedUser.getPassword());
            updatedUser = userRepository.save(userFound);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The current password it does not match!");
        }

        return UserMapper.userEntityToPasswordDto(updatedUser);
    }

}
