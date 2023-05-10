package com.disi.travelpoints.dto.mapper;

import com.disi.travelpoints.dto.TouristAttractionWishlistDto;
import com.disi.travelpoints.model.TouristAttractionWishlistEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TouristAttractionWishlistMapper {

    private final TouristAttractionMapper attractionMapper;
    private final WishlistMapper wishlistMapper;

    public TouristAttractionWishlistMapper(TouristAttractionMapper attractionMapper, WishlistMapper wishlistMapper) {
        this.attractionMapper = attractionMapper;
        this.wishlistMapper = wishlistMapper;
    }


    public TouristAttractionWishlistDto toDto(TouristAttractionWishlistEntity touristAttractionWishlistEntity) {
        return TouristAttractionWishlistDto.builder()
                .id(touristAttractionWishlistEntity.getId())
                .touristAttraction(attractionMapper.toDto(touristAttractionWishlistEntity.getTouristAttraction()))
                .wishlist(wishlistMapper.toDto(touristAttractionWishlistEntity.getWishlistEntity()))
                .build();
    }

    public List<TouristAttractionWishlistDto> toDto(List<TouristAttractionWishlistEntity> touristAttractionEntityList) {
        return touristAttractionEntityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
