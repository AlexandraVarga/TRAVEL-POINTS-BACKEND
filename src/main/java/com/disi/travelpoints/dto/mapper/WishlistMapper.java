package com.disi.travelpoints.dto.mapper;

import com.disi.travelpoints.dto.WishlistDto;
import com.disi.travelpoints.model.TouristAttractionWishlistEntity;
import com.disi.travelpoints.model.WishlistEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WishlistMapper {

    public WishlistDto toDto(WishlistEntity wishlistEntity) {
        return WishlistDto.builder()
                .id(wishlistEntity.getId())
                .clientId(wishlistEntity.getClientWishlist().getId())
                .attractionIds(getIdList(wishlistEntity.getTouristAttractionWishlistEntities()))
                .build();
    }

    private List<Integer> getIdList(List<TouristAttractionWishlistEntity> touristAttractionWishlistEntities) {
        List<Integer> idList = new ArrayList<>();
        for (TouristAttractionWishlistEntity obj : touristAttractionWishlistEntities) {
            idList.add(obj.getId());
        }
        return idList;
    }

}
