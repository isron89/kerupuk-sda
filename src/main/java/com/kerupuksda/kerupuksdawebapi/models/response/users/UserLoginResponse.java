package com.kerupuksda.kerupuksdawebapi.models.response.users;

import com.kerupuksda.kerupuksdawebapi.models.response.BaseResponse;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserLoginResponse extends BaseResponse implements Serializable {

    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private String roles;

}
