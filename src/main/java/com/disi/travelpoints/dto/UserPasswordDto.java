package com.disi.travelpoints.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
