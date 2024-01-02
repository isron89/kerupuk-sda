package com.kerupuksda.kerupuksdawebapi.repository;

import com.kerupuksda.kerupuksdawebapi.models.entity.React;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReactRepository extends JpaRepository<React, UUID> {

    @Query(value = "SELECT AVG(rating) FROM react WHERE productId = :productId", nativeQuery = true)
    Float getRatingProduct(@Param("productId") String productId);
}
