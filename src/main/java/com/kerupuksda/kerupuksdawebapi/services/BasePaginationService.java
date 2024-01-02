package com.kerupuksda.kerupuksdawebapi.services;

import com.kerupuksda.kerupuksdawebapi.models.request.BasePaginationRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.BasePaginationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PropertySource("classpath:pagination-default.properties")
public abstract class BasePaginationService<T extends BasePaginationRequest, V extends BasePaginationResponse> implements BaseService<T, V>{
	
	@Value("${default.page.size}")
    private Integer pageSize;

	@Value("${default.page.number}")
    private Integer pageNumber;
	
	@Value("${default.page.sortBy}")
    private String sortBy;
	
	@Value("${default.page.sortType}")
    private String sortType;
}
