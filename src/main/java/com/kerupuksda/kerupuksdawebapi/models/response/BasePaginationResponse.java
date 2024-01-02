package com.kerupuksda.kerupuksdawebapi.models.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BasePaginationResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5578484318986645502L;
	protected Pagination pagination;
}
