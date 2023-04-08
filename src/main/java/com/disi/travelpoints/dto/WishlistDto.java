package com.disi.travelpoints.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WishlistDto {

    private Integer id;

    private List<TouristAttractionDto> attractions;
}
