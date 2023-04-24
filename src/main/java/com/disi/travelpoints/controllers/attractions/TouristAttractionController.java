package com.disi.travelpoints.controllers.attractions;

import com.disi.travelpoints.dto.TouristAttractionDto;
import com.disi.travelpoints.services.TouristAttractionService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourist-attractions")
@CrossOrigin
public class TouristAttractionController {

    private final TouristAttractionService touristAttractionService;

    public TouristAttractionController(TouristAttractionService touristAttractionService) {
        this.touristAttractionService = touristAttractionService;
    }

    @GetMapping()
    public List<TouristAttractionDto> getAllAttractions() {
        return touristAttractionService.getAllAttractions();
    }

    @PostMapping()
    public TouristAttractionDto createAttraction(@RequestBody TouristAttractionDto touristAttractionDto) {
        return touristAttractionService.createAttraction(touristAttractionDto);
    }

    @PutMapping("/{attractionId}")
    public TouristAttractionDto updateAttraction(@RequestBody TouristAttractionDto touristAttractionDto, @PathVariable Integer attractionId) {
        return touristAttractionService.updateAttraction(touristAttractionDto, attractionId);
    }

    @DeleteMapping("/{attractionId}")
    public ResponseEntity<String> deleteTouristAttraction(@PathVariable @NotNull Integer attractionId) {
        touristAttractionService.deleteAttraction(attractionId);
        return new ResponseEntity<>("Tourist attraction deleted!", HttpStatus.OK);
    }
}
