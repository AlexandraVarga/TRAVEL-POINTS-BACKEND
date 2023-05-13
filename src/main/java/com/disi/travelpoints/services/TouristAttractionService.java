package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.IntervalSearchDto;
import com.disi.travelpoints.dto.TouristAttractionDto;
import com.disi.travelpoints.dto.TextSearchDto;
import com.disi.travelpoints.dto.mapper.TouristAttractionMapper;
import com.disi.travelpoints.model.TouristAttractionEntity;
import com.disi.travelpoints.repositories.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.disi.travelpoints.utils.TouristAttractionFiledNames.*;

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

    public List<TouristAttractionDto> searchAttractions(TextSearchDto textSearchDto) {
        List<TouristAttractionEntity> touristAttractions;
        String filterValue = textSearchDto.getValue();
        switch (textSearchDto.getFilterKey()) {
            case NAME -> touristAttractions = touristAttractionRepository.findAllByNameContainsIgnoreCase(filterValue);
            case LOCATION -> touristAttractions = touristAttractionRepository.findAllByLocationContainsIgnoreCase(filterValue);
            case TEXT_DESCRIPTION -> touristAttractions = touristAttractionRepository.findAllByTextDescriptionContainsIgnoreCase(filterValue);
            case VISITING_DATE -> touristAttractions = touristAttractionRepository.findAllByVisitingDateContains(filterValue);
            default -> throw new RuntimeException("Filter unknown!");
        }
        return touristAttractionMapper.toDto(touristAttractions);
    }

    public List<TouristAttractionDto> searchAttractionsByInterval(IntervalSearchDto intervalSearchDto) {
        List<TouristAttractionEntity> touristAttractions;
        Long intervalStart = intervalSearchDto.getStart();
        Long intervalEnd = intervalSearchDto.getEnd();
        switch (intervalSearchDto.getFilterKey()) {
            case NR_OF_VISITS -> touristAttractions = touristAttractionRepository.findAllByNrOfVisitsBetween(intervalStart, intervalEnd);
            case ENTRY_PRICE -> touristAttractions = touristAttractionRepository.findAllByEntryPriceBetween(intervalStart, intervalEnd);
            case DISCOUNT -> touristAttractions = touristAttractionRepository.findAllByDiscountBetween(intervalStart, intervalEnd);
            default -> throw new RuntimeException("Filter unknown!");
        }
        return touristAttractionMapper.toDto(touristAttractions);
    }

}
