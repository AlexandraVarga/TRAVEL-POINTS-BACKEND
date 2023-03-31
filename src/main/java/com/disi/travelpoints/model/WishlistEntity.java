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
@Table(name = "wishlist")
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientWishlist;

    @OneToMany(mappedBy = "wishlistEntity")
    private List<TouristAttractionWishlistEntity> touristAttractionWishlistEntities;
}
