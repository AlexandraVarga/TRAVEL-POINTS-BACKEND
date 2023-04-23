package com.disi.travelpoints.repositories;

import com.disi.travelpoints.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("select u.password from UserEntity u where u.username =:username")
    String findPasswordForUser(@Param("username") String username);

    UserEntity findByUsername(String username);

}
