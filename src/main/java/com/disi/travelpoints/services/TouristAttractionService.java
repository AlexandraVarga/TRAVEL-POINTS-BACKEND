package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.TouristAttractionDto;
import com.disi.travelpoints.dto.mapper.TouristAttractionMapper;
import com.disi.travelpoints.model.TouristAttractionEntity;
import com.disi.travelpoints.repositories.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristAttractionService {

    private final TouristAttractionRepository touristAttractionRepository;
    private final TouristAttractionMapper touristAttractionMapper;

    public TouristAttractionService(TouristAttractionRepository touristAttractionRepository, TouristAttractionMapper touristAttractionMapper) {
        this.touristAttractionRepository = touristAttractionRepository;
        this.touristAttractionMapper = touristAttractionMapper;
    }

    public List<TouristAttractionDto> getAllAttractions() {
        return touristAttractionMapper.toDto(touristAttractionRepository.findAll());
    }

    public TouristAttractionDto createAttraction(TouristAttractionDto touristAttractionDto) {
        TouristAttractionEntity touristAttractionEntity = touristAttractionMapper.toEntity(touristAttractionDto);
        TouristAttractionEntity newAttraction = touristAttractionRepository.save(touristAttractionEntity);

        return touristAttractionMapper.toDto(newAttraction);
    }

    public TouristAttractionDto updateAttraction(TouristAttractionDto touristAttractionDto, Integer attractionId) {
        TouristAttractionEntity touristAttractionFound = findTouristAttractionEntity(attractionId);

        TouristAttractionEntity updatedAttractionEntity = touristAttractionMapper.toEntity(touristAttractionDto);
        touristAttractionFound.setName(updatedAttractionEntity.getName());
        touristAttractionFound.setLocation(updatedAttractionEntity.getLocation());
        touristAttractionFound.setTextDescription(updatedAttractionEntity.getTextDescription());
        touristAttractionFound.setEntryPrice(updatedAttractionEntity.getEntryPrice());
        touristAttractionFound.setDiscount(updatedAttractionEntity.getDiscount());
        touristAttractionFound.setVisitingDate(updatedAttractionEntity.getVisitingDate());
        TouristAttractionEntity savedAttraction = touristAttractionRepository.save(touristAttractionFound);

        return touristAttractionMapper.toDto(savedAttraction);
    }

    private TouristAttractionEntity findTouristAttractionEntity(Integer attractionId) {
        return touristAttractionRepository.findById(attractionId)
                .orElseThrow(() -> new IllegalArgumentException("Attraction with id " + attractionId + " doesn't exist"));
    }

    public void deleteAttraction(Integer attractionId) {
        findTouristAttractionEntity(attractionId);
        touristAttractionRepository.deleteById(attractionId);
    }
}
