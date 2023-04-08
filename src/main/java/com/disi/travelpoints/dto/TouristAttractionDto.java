package com.disi.travelpoints.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TouristAttractionDto {

    private Integer id;

    private String name;

    private String location;

    private String textDescription;

    private Long nrOfVisits;

    private Double entryPrice;

    private Double discount;

    private String visitingDate;
}
