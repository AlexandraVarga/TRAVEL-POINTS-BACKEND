package com.disi.travelpoints.repositories;

import com.disi.travelpoints.model.TouristAttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristAttractionRepository extends JpaRepository<TouristAttractionEntity, Integer> {

    @Query("select t from TouristAttractionEntity t where t.id in :idList")
    List<TouristAttractionEntity> findAllByIdList(@Param("idList") List<Integer> idList);

    List<TouristAttractionEntity> findAllByNameContainsIgnoreCase(String name);

    List<TouristAttractionEntity> findAllByLocationContainsIgnoreCase(String location);

    List<TouristAttractionEntity> findAllByTextDescriptionContainsIgnoreCase(String textDescription);

    List<TouristAttractionEntity> findAllByNrOfVisitsBetween(long start, long end);

    List<TouristAttractionEntity> findAllByEntryPriceBetween(double start, double end);

    List<TouristAttractionEntity> findAllByDiscountBetween(double start, double end);

    List<TouristAttractionEntity> findAllByVisitingDateContains(String visitingDate);

}
