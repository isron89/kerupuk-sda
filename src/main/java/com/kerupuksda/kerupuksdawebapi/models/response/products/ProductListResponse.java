package com.kerupuksda.kerupuksdawebapi.models.response.products;

import com.kerupuksda.kerupuksdawebapi.models.response.BasePaginationResponse;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductListResponse extends BasePaginationResponse {

    private List<ProductResponse> data;

}
