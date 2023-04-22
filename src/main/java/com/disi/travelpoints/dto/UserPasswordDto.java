package com.disi.travelpoints.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPasswordDto {

    private String currentPassword;

    private String newPassword;
}
