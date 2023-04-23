package com.disi.travelpoints.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TouristAttractionDto {

    private Integer id;

    private String name;

    private String location;

    private String textDescription;

    private Long nrOfVisits;

    private Double entryPrice;

    private Double discount;

    private String visitingDate;

    private String image;
}
