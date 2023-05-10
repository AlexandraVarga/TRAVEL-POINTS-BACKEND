package com.disi.travelpoints.controllers.wishlist;

import com.disi.travelpoints.dto.TouristAttractionDto;
import com.disi.travelpoints.dto.WishlistDto;
import com.disi.travelpoints.services.WishlistService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{clientId}")
    public List<TouristAttractionDto> viewWishlist(@PathVariable @Valid @Min(value = 1, message = "Client's id must be  > 0") Integer clientId) {
        return wishlistService.viewOwnWishlist(clientId);
    }

    @PostMapping("/client/{clientId}/attraction/{attractionId}")
    public WishlistDto addAttractionToWishlist(@PathVariable @Min(value = 1, message = "Client's id must be  > 0") Integer clientId, @PathVariable Integer attractionId) {
        return wishlistService.addAttractionToWishlist(clientId, attractionId);
    }
}
