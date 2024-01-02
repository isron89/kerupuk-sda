package com.kerupuksda.kerupuksdawebapi.models.request.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateUserRequest extends BaseRequest implements Serializable {

    @NotBlank(message = "Id cannot be null or empty")
    @Size(max = 36, message = "Id has maximal 36 character UUID")
    private String id;
    private String username;
    private String email;
    private String password;
    private String fullname;
    private String phoneNo;
    private String address;
    private Integer status;
    @JsonIgnore
    private String role;
    @JsonIgnore
    private String usernameLoggedUser;

}
