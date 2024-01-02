package com.kerupuksda.kerupuksdawebapi.models.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pagination implements Serializable {
	private Integer pageSize;
	private Integer currentPage;
	private Integer totalPages;
	private Long totalRecords;
	private Boolean isFirstPage;
	private Boolean isLastPage;
}
