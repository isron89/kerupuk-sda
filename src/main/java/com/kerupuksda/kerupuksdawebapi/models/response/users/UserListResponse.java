package com.kerupuksda.kerupuksdawebapi.models.response.users;

import com.kerupuksda.kerupuksdawebapi.models.response.BaseResponse;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserListResponse extends BaseResponse {

    private List<UserResponse> data;

}
