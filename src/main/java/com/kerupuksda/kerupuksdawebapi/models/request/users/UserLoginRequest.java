package com.kerupuksda.kerupuksdawebapi.models.request.users;

import com.kerupuksda.kerupuksdawebapi.models.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLoginRequest extends BaseRequest implements Serializable {

    @NotBlank(message = "Username tidak boleh kosong")
    private String username;
    @NotBlank(message = "Password tidak boleh kosong")
    private String password;

}
