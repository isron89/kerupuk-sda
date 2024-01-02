package com.kerupuksda.kerupuksdawebapi.models.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValidationResponse extends BaseResponse implements Serializable {

    private Boolean result;

}
