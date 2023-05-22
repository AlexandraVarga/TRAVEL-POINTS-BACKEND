package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.HttpMessageDto;
import com.disi.travelpoints.dto.LoginDto;
import com.disi.travelpoints.dto.RegisterDto;
import com.disi.travelpoints.dto.UserDto;
import com.disi.travelpoints.dto.mapper.UserMapper;
import com.disi.travelpoints.model.ClientEntity;
import com.disi.travelpoints.model.UserEntity;
import com.disi.travelpoints.repositories.ClientRepository;
import com.disi.travelpoints.repositories.UserRepository;
import com.disi.travelpoints.utils.RoleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.security.MessageDigest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String ADDRESS = "address";
    private static final String AGECATEGORY = "ageCategory";

    private static final String CLIENT_USERNAME = "username";
    private static final String CLIENT_ADDRESS = "address";

    private static final String REGISTER_MESSAGE = "User registered successfully!";

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageDigest messageDigest;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private static final UserEntity user = getUserEntity();
    private static final RegisterDto registerDto = getRegisterDTO();

    @Test
    void register() {
        when(userMapper.toEntity(registerDto)).thenReturn(user);
        when(userMapper.clientToEntity(registerDto)).thenReturn(new ClientEntity());
        when(userRepository.save(any(UserEntity.class))).then(returnsFirstArg());
        when(clientRepository.save(any(ClientEntity.class))).then(returnsFirstArg());

        HttpMessageDto httpMessageDto = userService.register(registerDto);
        assertThat(httpMessageDto.getErrorMessage()).isEqualTo(REGISTER_MESSAGE);
        assertThat(httpMessageDto.getHttpStatus()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void login() {
        LoginDto loginDto = new LoginDto("username", "password");
        UserDto userDto = new UserDto(1, loginDto.getUsername(), RoleEnum.ROLE_CLIENT.toString());
        when(userMapper.toDto(user)).thenReturn(userDto);
        when(userRepository.findByUsername(any(String.class))).thenReturn(user);
        when(userRepository.existsByUsername(any(String.class))).thenReturn(true);
        when(userRepository.findPasswordForUser(any(String.class))).thenReturn("5f4dcc3b5aa765d61d8327deb882cf99");

        UserDto loggedUser = userService.login(loginDto);
        assertThat(loggedUser).isEqualTo(userDto);
    }

    static RegisterDto getRegisterDTO() {
        return RegisterDto.builder()
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .address(ADDRESS)
                .ageCategory(AGECATEGORY)
                .build();
    }

    static UserEntity getUserEntity() {
        return UserEntity.builder()
                .username(CLIENT_USERNAME)
                .email(CLIENT_ADDRESS)
                .password(PASSWORD)
                .build();
    }

}
