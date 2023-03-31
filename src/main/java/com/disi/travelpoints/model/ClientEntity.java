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
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "age_category")
    private String ageCategory;

    @OneToMany(mappedBy = "client")
    private List<ReviewEntity> clientReviews;

    @OneToMany(mappedBy = "clientWishlist")
    private List<WishlistEntity> clientsWishlists;

}
