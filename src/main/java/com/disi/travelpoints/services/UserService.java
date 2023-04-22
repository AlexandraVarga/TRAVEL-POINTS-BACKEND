package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.*;
import com.disi.travelpoints.dto.mapper.UserMapper;
import com.disi.travelpoints.model.ClientEntity;
import com.disi.travelpoints.model.UserEntity;
import com.disi.travelpoints.repositories.ClientRepository;
import com.disi.travelpoints.repositories.UserRepository;
import com.disi.travelpoints.utils.RoleEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, ClientRepository clientRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.userMapper = userMapper;
    }

    public String generatePasswordHash(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }


    public HttpMessageDto register(RegisterDto registerDto) {
        HttpMessageDto httpStatus = validateUsernameAndEmail(registerDto);
        if (httpStatus != null) {
            return httpStatus;
        }

        UserEntity user = userMapper.toEntity(registerDto);
        ClientEntity client = userMapper.clientToEntity(registerDto);
        client.setUser(user);
        user.setPassword(generatePasswordHash(registerDto.getPassword()));
        user.setUserRole(RoleEnum.ROLE_CLIENT);
        userRepository.save(user);
        clientRepository.save(client);
        return new HttpMessageDto("User registered successfully!", HttpStatus.OK);
    }

    private HttpMessageDto validateUsernameAndEmail(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new HttpMessageDto("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new HttpMessageDto("Error: Email is already in use!", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public UserDto login(LoginDto loginDto) {
        String loginPassword = loginDto.getPassword();
        UserEntity userFound = userRepository.findByUsername(loginDto.getUsername());
        if (userFound == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found!");
        }

        String hashedPassword = generatePasswordHash(loginPassword);
        boolean existsByUsername = userRepository.existsByUsername(userFound.getUsername());
        String userPassword = userRepository.findPasswordForUser(loginDto.getUsername());

        if (existsByUsername && hashedPassword.equals(userPassword)) {
            return userMapper.toDto(userFound);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The credentials are not correct!");
        }

    }

    public UserPasswordDto changePassword(UserPasswordDto userPasswordDto, Integer userId) {
        UserEntity userFound = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " doesn't exist"));

        String currentPassword = userPasswordDto.getCurrentPassword();
        String savedPassword = generatePasswordHash(currentPassword);
        UserEntity updatedUser = userMapper.passwordDtoToEntity(userPasswordDto);

        if (userFound.getPassword().equals(savedPassword)) {
            userFound.setPassword(generatePasswordHash(updatedUser.getPassword()));
            updatedUser = userRepository.save(userFound);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The current password it does not match!");
        }

        return userMapper.userEntityToPasswordDto(updatedUser);
    }


}
