package com.disi.travelpoints.dto.mapper;

import com.disi.travelpoints.dto.RegisterDto;
import com.disi.travelpoints.dto.UserDto;
import com.disi.travelpoints.dto.UserPasswordDto;
import com.disi.travelpoints.model.ClientEntity;
import com.disi.travelpoints.model.UserEntity;
import com.disi.travelpoints.security.UserDetailsImpl;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity toEntity(RegisterDto registerDto) {
        return UserEntity.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .build();
    }

    public static ClientEntity clientToEntity(RegisterDto registerDto) {
        return ClientEntity.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .address(registerDto.getAddress())
                .ageCategory(registerDto.getAgeCategory())
                .build();
    }

    public static UserDto toDto(UserDetailsImpl userDetails) {
        return UserDto.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
//                .email(userDetails.getEmail())
                .userRole(userDetails.getAuthorities().stream().findFirst().get().getAuthority())
                .build();
    }

//    public static List<UserDto> entityToDto(List<UserEntity> userList) {
//        return userList.stream().map(toDto()).collect(Collectors.toList());
//    }

    public static UserEntity passwordDtoToEntity(UserPasswordDto userPasswordDto) {
        return UserEntity.builder()
                .password(userPasswordDto.getNewPassword())
                .build();
    }

    public static UserPasswordDto userEntityToPasswordDto(UserEntity userEntity) {
        return UserPasswordDto.builder()
                .newPassword(userEntity.getPassword())
                .build();
    }

}
