package com.disi.travelpoints.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WishlistDto {

    private Integer id;

    private Integer clientId;

    private List<Integer> attractionIds;
}
