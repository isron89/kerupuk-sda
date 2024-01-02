package com.kerupuksda.kerupuksdawebapi.models.response.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kerupuksda.kerupuksdawebapi.models.response.BaseResponse;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends BaseResponse {

    private String id;
    private String username;
    private String email;
    private String role;
    private String status;
    //details
    private String fullname;
    private String phoneNo;
    private String address;

}
