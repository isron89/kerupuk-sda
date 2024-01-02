package com.kerupuksda.kerupuksdawebapi.models.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestResponse<T> {
    private Boolean success;
    private String message;
    private T data;
}
