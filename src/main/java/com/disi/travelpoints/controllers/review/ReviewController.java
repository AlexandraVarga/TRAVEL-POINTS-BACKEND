package com.disi.travelpoints.controllers.review;

import com.disi.travelpoints.dto.ReviewDto;
import com.disi.travelpoints.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public List<ReviewDto> getReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/tourist-attraction/{touristAttractionId}")
    public List<ReviewDto> getReviewsByAttractionId(@PathVariable Integer touristAttractionId) {
        return reviewService.getAllReviewsByAttractionId(touristAttractionId);
    }

    @PostMapping("/client/{clientId}/attraction/{touristAttractionId}")
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto, @PathVariable Integer clientId, @PathVariable Integer touristAttractionId) {
        return reviewService.addReview(reviewDto, clientId, touristAttractionId);
    }
}
