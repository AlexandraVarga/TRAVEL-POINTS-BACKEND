package com.disi.travelpoints.dto.mapper;

import com.disi.travelpoints.dto.ReviewDto;
import com.disi.travelpoints.model.ReviewEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public ReviewEntity dtoToEntity(ReviewDto reviewDto) {
        return ReviewEntity.builder()
                .id(reviewDto.getId())
                .review(reviewDto.getReview())
                .build();
    }

    public ReviewDto entityToDto(ReviewEntity reviewEntity) {
        return ReviewDto.builder()
                .id(reviewEntity.getId())
                .review(reviewEntity.getReview())
                .clientId(reviewEntity.getClient().getId())
                .touristAttractionId(reviewEntity.getTouristAttractionReview().getId())
                .build();
    }

    public List<ReviewDto> entitiesToDtos(List<ReviewEntity> reviewEntityList) {
        return reviewEntityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
