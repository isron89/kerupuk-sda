package com.kerupuksda.kerupuksdawebapi.services.product;

import com.kerupuksda.kerupuksdawebapi.exception.BadRequestException;
import com.kerupuksda.kerupuksdawebapi.models.entity.Product;
import com.kerupuksda.kerupuksdawebapi.models.entity.User;
import com.kerupuksda.kerupuksdawebapi.models.request.products.ProductAddRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.ValidationResponse;
import com.kerupuksda.kerupuksdawebapi.repository.ProductRepository;
import com.kerupuksda.kerupuksdawebapi.repository.UserRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostProductAddService implements BaseService<ProductAddRequest, ValidationResponse> {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public ValidationResponse execute(ProductAddRequest input) {
        User user = userRepository.findById(UUID.fromString(input.getUserId())).orElseThrow(
                () -> new BadRequestException(AppConstants.USER_NOT_FOUND)
        );

        Product product = new Product();
        product.setNama(input.getNama());
        product.setDeskripsi(input.getDeskripsi());
        product.setBahan(input.getBahan());
        product.setCaraMembuat(input.getCaraMembuat());
        product.setHarga(setHarga(input.getHarga()));
        product.setIsPublic(setIsPublic(input.getIsPublic()));
        product.setUserId(user);
        product.setIsDeleted(false);
        product.setCreatedBy(user.getUsername());
        product.setCreatedDate(LocalDateTime.now());
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
}
