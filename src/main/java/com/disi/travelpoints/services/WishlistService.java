package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.TouristAttractionDto;
import com.disi.travelpoints.dto.WishlistDto;
import com.disi.travelpoints.dto.mapper.TouristAttractionMapper;
import com.disi.travelpoints.dto.mapper.WishlistMapper;
import com.disi.travelpoints.model.ClientEntity;
import com.disi.travelpoints.model.TouristAttractionEntity;
import com.disi.travelpoints.model.TouristAttractionWishlistEntity;
import com.disi.travelpoints.model.WishlistEntity;
import com.disi.travelpoints.repositories.ClientRepository;
import com.disi.travelpoints.repositories.TouristAttractionRepository;
import com.disi.travelpoints.repositories.TouristAttractionWishlistRepository;
import com.disi.travelpoints.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private final ClientRepository clientRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;
    private final TouristAttractionWishlistRepository touristAttractionWishlistRepository;
    private final TouristAttractionMapper touristAttractionMapper;

    public WishlistService(ClientRepository clientRepository, TouristAttractionRepository touristAttractionRepository, WishlistRepository wishlistRepository, WishlistMapper wishlistMapper, TouristAttractionWishlistRepository touristAttractionWishlistRepository, TouristAttractionMapper touristAttractionMapper) {
        this.clientRepository = clientRepository;
        this.touristAttractionRepository = touristAttractionRepository;
        this.wishlistRepository = wishlistRepository;
        this.wishlistMapper = wishlistMapper;
        this.touristAttractionWishlistRepository = touristAttractionWishlistRepository;
        this.touristAttractionMapper = touristAttractionMapper;
    }

    public List<TouristAttractionDto> viewOwnWishlist(Integer clientId) {
        clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client with " + clientId + " does not exist!"));

        List<Integer> wishlistIds = wishlistRepository.findWishlistsByClientId(clientId);
        List<Integer> attractionIds = touristAttractionWishlistRepository.findAllAttractionsByWishlistIds(wishlistIds);
        List<TouristAttractionEntity> attractionEntityList = touristAttractionRepository.findAllByIdList(attractionIds);

        return touristAttractionMapper.toDto(attractionEntityList);
    }

    public WishlistDto addAttractionToWishlist(Integer clientId, Integer attractionId) {
        List<TouristAttractionWishlistEntity> touristAttractionWishlist = new ArrayList<>();
        TouristAttractionEntity touristAttraction = touristAttractionRepository.findById(attractionId)
                .orElseThrow(() -> new IllegalArgumentException("Attraction with " + attractionId + " does not exist!"));

        WishlistEntity wishlist = createWishlist(touristAttractionWishlist, clientId);

        TouristAttractionWishlistEntity touristAttractionWishlistEntity = createTouristAttractionWishlist(touristAttraction, wishlist);
        touristAttractionWishlist.add(touristAttractionWishlistEntity);

        return wishlistMapper.toDto(wishlist);
    }

    private WishlistEntity createWishlist(List<TouristAttractionWishlistEntity> touristAttractionWishlist, Integer clientId) {
        ClientEntity client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client with " + clientId + " does not exist!"));

        WishlistEntity wishlist = new WishlistEntity();
        wishlist.setClientWishlist(client);
        wishlist.setTouristAttractionWishlistEntities(touristAttractionWishlist);
        return wishlistRepository.save(wishlist);
    }

    private TouristAttractionWishlistEntity createTouristAttractionWishlist(TouristAttractionEntity touristAttraction, WishlistEntity wishlist) {
        TouristAttractionWishlistEntity touristAttractionWishlistEntity = new TouristAttractionWishlistEntity();
        touristAttractionWishlistEntity.setTouristAttraction(touristAttraction);
        touristAttractionWishlistEntity.setWishlistEntity(wishlist);
        return touristAttractionWishlistRepository.save(touristAttractionWishlistEntity);
    }
}
