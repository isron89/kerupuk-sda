package com.kerupuksda.kerupuksdawebapi.services.product;

import com.kerupuksda.kerupuksdawebapi.exception.BadRequestException;
import com.kerupuksda.kerupuksdawebapi.models.entity.Product;
import com.kerupuksda.kerupuksdawebapi.models.request.products.ProductUpdateRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.ValidationResponse;
import com.kerupuksda.kerupuksdawebapi.repository.ProductRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PutProductUpdateService implements BaseService<ProductUpdateRequest, ValidationResponse> {

    private final ProductRepository productRepository;

    @Override
    public ValidationResponse execute(ProductUpdateRequest input) {

        Product product = productRepository.getProductById(input.getId(), true).orElseThrow(
                () -> new BadRequestException(AppConstants.PRODUCT_NOT_FOUND)
        );
        if (!product.getCreatedBy().equalsIgnoreCase(input.getUsername())) {
            throw new BadRequestException("Anda tidak mempunyai akses produk ini");
        }
        product.setNama(validationLength("Nama", input.getNama(), product.getNama(), 3, 100));
        product.setDeskripsi(validationLength("Deskripsi", input.getDeskripsi(), product.getDeskripsi(), 5, 1000));
        product.setBahan(validationLength("Bahan", input.getBahan(), product.getBahan(), 5, 2000));
        product.setCaraMembuat(validationLength("Cara Membuat", input.getCaraMembuat(), product.getCaraMembuat(), 5, 3000));
        product.setHarga(setHarga(input.getHarga()));
        product.setIsPublic(setIsPublic(input.getIsPublic()));
        product.setUpdatedBy(input.getUsername());
        product.setUpdatedDate(LocalDateTime.now());
        productRepository.save(product);

        return ValidationResponse.builder().result(true).build();
    }

    private Boolean setIsPublic(Boolean isPublic) {
        if (ObjectUtils.isEmpty(isPublic)){
            return Boolean.TRUE;
        }
        return isPublic;
    }

    private BigDecimal setHarga(BigDecimal harga) {
        if (ObjectUtils.isEmpty(harga)){
            return BigDecimal.ZERO;
        }
        return harga;
    }

    private String validationLength(String field, String content, String defaultContent, Integer minLength, Integer maxLength) {
        if (ObjectUtils.isEmpty(content)){
            return defaultContent;
        } else {
            if (content.length() < minLength || content.length() > maxLength) {
                throw new BadRequestException("Field " + field + " minimal " + minLength + " karakter dan maksimal "
                        + maxLength + " karakter");
            }
            return content;
        }
    }

}
