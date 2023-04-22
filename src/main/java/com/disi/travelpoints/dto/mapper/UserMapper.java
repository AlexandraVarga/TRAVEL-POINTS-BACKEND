package com.disi.travelpoints.dto.mapper;

import com.disi.travelpoints.dto.RegisterDto;
import com.disi.travelpoints.dto.UserDto;
import com.disi.travelpoints.dto.UserPasswordDto;
import com.disi.travelpoints.model.ClientEntity;
import com.disi.travelpoints.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(RegisterDto registerDto) {
        return UserEntity.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .build();
    }

    public ClientEntity clientToEntity(RegisterDto registerDto) {
        return ClientEntity.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .address(registerDto.getAddress())
                .ageCategory(registerDto.getAgeCategory())
                .build();
    }

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .userRole(userEntity.getUserRole().toString())
                .build();
    }

    public UserEntity passwordDtoToEntity(UserPasswordDto userPasswordDto) {
        return UserEntity.builder()
                .password(userPasswordDto.getNewPassword())
                .build();
    }

    public UserPasswordDto userEntityToPasswordDto(UserEntity userEntity) {
        return UserPasswordDto.builder()
                .newPassword(userEntity.getPassword())
                .build();
    }

}
