package com.kerupuksda.kerupuksdawebapi.services.product;

import com.kerupuksda.kerupuksdawebapi.models.entity.Product;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseSearchRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.products.ProductListResponse;
import com.kerupuksda.kerupuksdawebapi.models.response.products.ProductResponse;
import com.kerupuksda.kerupuksdawebapi.repository.ProductRepository;
import com.kerupuksda.kerupuksdawebapi.services.BasePaginationService;
import com.kerupuksda.kerupuksdawebapi.utils.AppUtils;
import com.kerupuksda.kerupuksdawebapi.utils.PageableUtil;
import com.kerupuksda.kerupuksdawebapi.utils.QueryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetProductPageService extends BasePaginationService<BaseSearchRequest, ProductListResponse> {

    private final ProductRepository productRepository;

    @Override
    public ProductListResponse execute(BaseSearchRequest request) {
        Page<Product> products = pagination(request);
        List<ProductResponse> productResponseList = products.stream().map(this::mappingProduct).collect(Collectors.toList());
        ProductListResponse response = new ProductListResponse();
        response.setData(productResponseList);
        response.setPagination(PageableUtil.pageToPagination(products));
        return response;
    }

    private Page<Product> pagination(BaseSearchRequest input) {
        Integer pageSize = input.getPageSize() != null ? input.getPageSize() : getPageSize();
        String sortBy = StringUtils.isEmpty(input.getSortBy()) ? getSortBy() : input.getSortBy();
        String sortType = StringUtils.isEmpty(input.getSortType()) ? getSortType() : input.getSortType();

        Pageable pageRequest = PageableUtil.createPageRequestNative(input, pageSize, getPageNumber(), sortBy, sortType,
                Product.class);
        return productRepository.getAllProductPagination(pageRequest, QueryUtils.queryLikeFormatter(
                QueryUtils.searchNameLowerCase(input.getSearch())), true);
    }

    private ProductResponse mappingProduct(Product product) {
        return ProductResponse.builder()
                .id(product.getId().toString())
                .nama(product.getNama())
                .deskripsi(product.getDeskripsi())
                .bahan(product.getBahan())
                .caraMembuat(product.getCaraMembuat())
//                .harga(product.getHarga())
                .tanggalBuat(AppUtils.formatDateTime(product.getCreatedDate()))
                .isPublic(product.getIsPublic())
                .build();
    }

}
