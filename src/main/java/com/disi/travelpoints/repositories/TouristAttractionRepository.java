package com.disi.travelpoints.repositories;

import com.disi.travelpoints.model.TouristAttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristAttractionRepository extends JpaRepository<TouristAttractionEntity, Integer> {
}
