package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.ReviewDto;
import com.disi.travelpoints.dto.mapper.ReviewMapper;
import com.disi.travelpoints.model.ClientEntity;
import com.disi.travelpoints.model.ReviewEntity;
import com.disi.travelpoints.model.TouristAttractionEntity;
import com.disi.travelpoints.repositories.ClientRepository;
import com.disi.travelpoints.repositories.ReviewRepository;
import com.disi.travelpoints.repositories.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final ClientRepository clientRepository;
    private final TouristAttractionRepository touristAttractionRepository;

    public ReviewService(ReviewMapper reviewMapper, ReviewRepository reviewRepository, ClientRepository clientRepository, TouristAttractionRepository touristAttractionRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
        this.clientRepository = clientRepository;
        this.touristAttractionRepository = touristAttractionRepository;
    }

    public List<ReviewDto> getAllReviews() {
        return reviewMapper.entitiesToDtos(reviewRepository.findAll());
    }

    public List<ReviewDto> getAllReviewsByAttractionId(Integer attractionId) {
        return reviewMapper.entitiesToDtos(reviewRepository.findAllByTouristAttractionId(attractionId));
    }

    public ReviewDto addReview(ReviewDto reviewDto, Integer clientId, Integer touristAttractionId) {
        ClientEntity clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("The client is not found!"));
        TouristAttractionEntity touristAttractionEntity = touristAttractionRepository.findById(touristAttractionId)
                .orElseThrow(() -> new IllegalArgumentException("The tourist attraction does not exist!"));

        ReviewEntity reviewEntity = reviewMapper.dtoToEntity(reviewDto);
        reviewEntity.setClient(clientEntity);
        reviewEntity.setTouristAttractionReview(touristAttractionEntity);
        ReviewEntity newReview = reviewRepository.save(reviewEntity);

        return reviewMapper.entityToDto(newReview);
    }
}
