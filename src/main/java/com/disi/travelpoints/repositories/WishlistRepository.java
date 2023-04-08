package com.disi.travelpoints.repositories;

import com.disi.travelpoints.model.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Integer> {
}
