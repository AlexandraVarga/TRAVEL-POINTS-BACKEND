package com.disi.travelpoints.repositories;

import com.disi.travelpoints.model.TouristAttractionWishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TouristAttractionWishlistRepository extends JpaRepository<TouristAttractionWishlistEntity, Integer> {

    @Query("select taw.touristAttraction.id from TouristAttractionWishlistEntity taw where taw.wishlistEntity.id in :wishlist")
    List<Integer> findAllAttractionsByWishlistIds(@Param("wishlist") List<Integer> wishlist);

}
