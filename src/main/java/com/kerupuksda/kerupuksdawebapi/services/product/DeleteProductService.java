package com.kerupuksda.kerupuksdawebapi.services.product;

import com.kerupuksda.kerupuksdawebapi.exception.BadRequestException;
import com.kerupuksda.kerupuksdawebapi.models.entity.Product;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseIdRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.ValidationResponse;
import com.kerupuksda.kerupuksdawebapi.repository.ProductRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteProductService implements BaseService<BaseIdRequest, ValidationResponse> {

    private final ProductRepository productRepository;

    @Override
    public ValidationResponse execute(BaseIdRequest request) {
        Product product = productRepository.findById(UUID.fromString(request.getId())).orElseThrow(
                () -> new BadRequestException(AppConstants.PRODUCT_NOT_FOUND)
        );

        product.setIsDeleted(true);
        product.setUpdatedBy(request.getUsername());
        product.setUpdatedDate(LocalDateTime.now());
        productRepository.save(product);

        return ValidationResponse.builder().result(true).build();
    }

}
