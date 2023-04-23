package com.disi.travelpoints.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tourist_attraction")
public class TouristAttractionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "text_description")
    private String textDescription;

    @Column(name = "nr_visits")
    private Long nrOfVisits;

    @Column(name = "entry_price")
    private Double entryPrice;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "visiting_date")
    private String visitingDate;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "touristAttractionReview")
    private List<ReviewEntity> touristAttractionReviewList;

    @OneToMany(mappedBy = "touristAttraction")
    private List<TouristAttractionWishlistEntity> touristAttractionWishlists;
}
