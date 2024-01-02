package com.kerupuksda.kerupuksdawebapi.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSearchRequest extends BasePaginationRequest {

    @ApiModelProperty(value="Common Text Search")
    private String search;

    @ApiModelProperty(value="Status")
    private Integer status;

    @ApiModelProperty(value="Deleted Status")
    private Integer isDeleted;

    @ApiModelProperty(value="Start Date")
    private String startDate;

    @ApiModelProperty(value="End Date")
    private String endDate;

}