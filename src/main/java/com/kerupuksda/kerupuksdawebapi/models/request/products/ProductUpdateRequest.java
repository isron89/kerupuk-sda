package com.kerupuksda.kerupuksdawebapi.models.request.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductUpdateRequest extends BaseRequest implements Serializable {

    @NotBlank(message = "Id produk tidak boleh kosong")
    @Size(max = 36, message = "Id produk maksimal 36 karakter UUID")
    private String id;
    private String nama;
    private String deskripsi;
    private String bahan;
    private String caraMembuat;
    private BigDecimal harga;
    private Boolean isPublic;
    @JsonIgnore
    private String username;

}
