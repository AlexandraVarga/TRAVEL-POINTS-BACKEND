package com.disi.travelpoints.dto.mapper;

import com.disi.travelpoints.dto.TouristAttractionDto;
import com.disi.travelpoints.model.TouristAttractionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TouristAttractionMapper {

    public TouristAttractionEntity toEntity(TouristAttractionDto touristAttractionDto) {
        return TouristAttractionEntity.builder()
                .id(touristAttractionDto.getId())
                .name(touristAttractionDto.getName())
                .location(touristAttractionDto.getLocation())
                .textDescription(touristAttractionDto.getTextDescription())
                .nrOfVisits(touristAttractionDto.getNrOfVisits())
                .entryPrice(touristAttractionDto.getEntryPrice())
                .discount(touristAttractionDto.getDiscount())
                .visitingDate(touristAttractionDto.getVisitingDate())
                .build();
    }

    public TouristAttractionDto toDto(TouristAttractionEntity touristAttractionEntity) {
        return TouristAttractionDto.builder()
                .id(touristAttractionEntity.getId())
                .name(touristAttractionEntity.getName())
                .location(touristAttractionEntity.getLocation())
                .textDescription(touristAttractionEntity.getTextDescription())
                .nrOfVisits(touristAttractionEntity.getNrOfVisits())
                .entryPrice(touristAttractionEntity.getEntryPrice())
                .discount(touristAttractionEntity.getDiscount())
                .visitingDate(touristAttractionEntity.getVisitingDate())
                .build();
    }

    public List<TouristAttractionDto> toDto(List<TouristAttractionEntity> touristAttractionEntityList) {
        return touristAttractionEntityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
