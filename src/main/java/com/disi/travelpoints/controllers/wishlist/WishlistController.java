package com.disi.travelpoints.controllers.wishlist;

import com.disi.travelpoints.dto.TouristAttractionDto;
import com.disi.travelpoints.dto.WishlistDto;
import com.disi.travelpoints.services.WishlistService;
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
    public List<TouristAttractionDto> viewWishlist(@PathVariable Integer clientId) {
        return wishlistService.viewOwnWishlist(clientId);
    }

    @PostMapping("/client/{clientId}/attraction/{attractionId}")
    public WishlistDto addAttractionToWishlist(@PathVariable Integer clientId, @PathVariable Integer attractionId) {
        return wishlistService.addAttractionToWishlist(clientId, attractionId);
    }
}
