package com.disi.travelpoints.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    private String ageCategory;

}
