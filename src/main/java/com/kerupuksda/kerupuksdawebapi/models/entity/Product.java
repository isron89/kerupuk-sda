package com.kerupuksda.kerupuksdawebapi.models.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "product", indexes = {
        @Index(name = "product_indx_0", columnList = "nama"),
        @Index(name = "product_indx_1", columnList = "deskripsi"),
        @Index(name = "product_indx_2", columnList = "bahan"),
        @Index(name = "product_indx_3", columnList = "cara_membuat")
})
public class Product extends BaseEntity {

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "bahan")
    private String bahan;

    @Column(name = "cara_membuat")
    private String caraMembuat;

    @Column(name = "harga")
    private BigDecimal harga;

    @Column(name = "is_public")
    private Boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userId;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isDeleted;

}
