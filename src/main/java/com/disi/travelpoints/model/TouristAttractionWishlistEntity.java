package com.disi.travelpoints.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tourist_attraction_wishlist")
public class TouristAttractionWishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private WishlistEntity wishlistEntity;

    @ManyToOne
    @JoinColumn(name = "tourist_attraction_id")
    private TouristAttractionEntity touristAttraction;

}
