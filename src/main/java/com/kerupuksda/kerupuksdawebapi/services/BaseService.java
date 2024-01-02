package com.kerupuksda.kerupuksdawebapi.services;

import com.kerupuksda.kerupuksdawebapi.models.request.BaseRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.BaseResponse;

public interface BaseService<T extends BaseRequest, V extends BaseResponse> {
    V execute(T input);
}
