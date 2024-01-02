package com.kerupuksda.kerupuksdawebapi.services.product;

import com.kerupuksda.kerupuksdawebapi.exception.BadRequestException;
import com.kerupuksda.kerupuksdawebapi.models.entity.Product;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseIdRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.products.ProductResponse;
import com.kerupuksda.kerupuksdawebapi.repository.ProductRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import com.kerupuksda.kerupuksdawebapi.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetProductDetailService implements BaseService<BaseIdRequest, ProductResponse> {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse execute(BaseIdRequest request) {
        Product product = productRepository.getProductById(request.getId(), true).orElseThrow(
                () -> new BadRequestException(AppConstants.PRODUCT_NOT_FOUND)
        );

        return mappingProduct(product);
    }

    private ProductResponse mappingProduct(Product product) {
        return ProductResponse.builder()
                .id(product.getId().toString())
                .nama(product.getNama())
                .deskripsi(product.getDeskripsi())
                .bahan(product.getBahan())
                .caraMembuat(product.getCaraMembuat())
//                .harga(product.getHarga())
                .isPublic(product.getIsPublic())
                .tanggalBuat(AppUtils.formatDateTime(product.getCreatedDate()))
                .pembuat(product.getCreatedBy())
                .build();
    }

}
