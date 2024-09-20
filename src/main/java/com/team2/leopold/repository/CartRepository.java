package com.team2.leopold.repository;

import com.team2.leopold.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c WHERE c.product.uid = :productUid AND c.user.uid = :userUid")
    Optional<Cart> findByProductUidAndUserUid(@Param("productUid") Integer productUid, @Param("userUid") Integer userUid);
}