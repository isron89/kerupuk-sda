package com.kerupuksda.kerupuksdawebapi.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BasePaginationRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1088344338839064492L;

	@JsonProperty("page_size")
    private Integer pageSize;
	
	@JsonProperty("page_number")
    private Integer pageNumber;
	
	@JsonProperty("sort_by")
    private String sortBy;
	
	@JsonProperty("sort_type")
    private String sortType;

}