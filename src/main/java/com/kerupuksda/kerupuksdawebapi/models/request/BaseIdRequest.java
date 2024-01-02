package com.kerupuksda.kerupuksdawebapi.models.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseIdRequest extends BaseRequest implements Serializable {

    @NotBlank(message = "Id cannot null or empty")
    private String id;

    @JsonIgnore
    private String username;

}
