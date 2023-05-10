package com.disi.travelpoints.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TouristAttractionWishlistDto {

    private Integer id;

    private WishlistDto wishlist;

    private TouristAttractionDto touristAttraction;
}
