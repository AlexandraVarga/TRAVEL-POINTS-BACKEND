package com.disi.travelpoints.repositories;

import com.disi.travelpoints.model.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Integer> {

    @Query("select w.id from WishlistEntity w where w.clientWishlist.id = :clientId")
    List<Integer> findWishlistsByClientId(@Param("clientId") Integer clientId);
}
