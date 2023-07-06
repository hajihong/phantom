package com.mimikyu.phantom.repository;

import com.mimikyu.phantom.domain.user.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findSellerByEmail(String email);

    boolean existsByEmail(String email);
}
