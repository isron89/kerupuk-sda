package com.kerupuksda.kerupuksdawebapi.models.request.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRegisterRequest extends BaseRequest implements Serializable {

    @NotBlank(message = "Username cannot be null or empty")
    @Size(min = 4, max = 100, message = "Username has minimum 3 character and maximal 100 character")
    private String username;
    @Email
    @NotBlank(message = "Email cannot be null or empty")
    @Size(min = 4, max = 200, message = "Email has minimum 4 character and maximal 200 character")
    private String email;
    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 8, max = 200, message = "Password has minimum 8 character and maximal 200 character")
    private String password;
    @NotBlank(message = "Fullname cannot be null or empty")
    @Size(min = 4, max = 200, message = "Fullname has minimum 4 character and maximal 200 character")
    private String fullname;
    @NotBlank(message = "Phone number cannot be null or empty")
    @Size(min = 8, max = 50, message = "Phone number has minimum 8 character and maximal 50 character")
    private String phoneNo;
    @NotBlank(message = "Address cannot be null or empty")
    @Size(min = 4, max = 200, message = "Address has minimum 4 character and maximal 200 character")
    private String address;
    @JsonIgnore
    private String role;

}
