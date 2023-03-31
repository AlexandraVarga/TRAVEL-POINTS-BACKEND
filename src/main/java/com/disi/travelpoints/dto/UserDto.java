package com.disi.travelpoints.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Integer id;

    private String username;

    private String password;

    private String userRole;
}
