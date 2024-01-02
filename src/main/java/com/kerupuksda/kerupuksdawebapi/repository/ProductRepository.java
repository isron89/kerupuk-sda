package com.kerupuksda.kerupuksdawebapi.repository;

import com.kerupuksda.kerupuksdawebapi.models.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT * FROM PRODUCT WHERE ID = :productId AND IS_DELETED = false AND IS_PUBLIC = :isPublic", nativeQuery = true)
    Optional<Product> getProductById(@Param("productId") String productId,
                                     @Param("isPublic") Boolean isPublic);

    @Query(value = "SELECT * FROM PRODUCT WHERE IS_DELETED = false AND IS_PUBLIC = :isPublic AND (" +
            "(lower(coalesce(NAMA, :search)) like :search) OR " +
            "(lower(coalesce(DESKRIPSI, :search)) like :search) OR " +
            "(lower(coalesce(BAHAN, :search)) like :search) OR " +
            "(lower(coalesce(CARA_MEMBUAT, :search)) like :search)) ",
            countQuery = "SELECT COUNT(1) FROM PRODUCT WHERE IS_DELETED = false AND IS_PUBLIC = :isPublic AND (" +
                    "(lower(coalesce(NAMA, :search)) like :search) OR " +
                    "(lower(coalesce(DESKRIPSI, :search)) like :search) OR " +
                    "(lower(coalesce(BAHAN, :search)) like :search) OR " +
                    "(lower(coalesce(CARA_MEMBUAT, :search)) like :search)) ",
            nativeQuery = true)
    Page<Product> getAllProductPagination(Pageable pageable,
                                          @Param("search") String search,
                                          @Param("isPublic") Boolean isPublic);

}
