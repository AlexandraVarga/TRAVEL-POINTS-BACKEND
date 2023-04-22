package com.disi.travelpoints.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    private String username;

    private String userRole;

}
