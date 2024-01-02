package com.kerupuksda.kerupuksdawebapi.models.request.products;

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
public class ProductAddRequest extends BaseRequest implements Serializable {

    @NotBlank(message = "Nama produk tidak boleh kosong")
    @Size(min = 3, max = 100, message = "Nama produk minimal 3 karakter dan maksimal 100 karakter")
    private String nama;
    @NotBlank(message = "Deskripsi produk tidak boleh kosong")
    @Size(min = 5, max = 1000, message = "Deskripsi produk minimal 5 karakter dan maksimal 1000 karakter")
    private String deskripsi;
    @NotBlank(message = "Bahan tidak boleh kosong")
    @Size(min = 5, max = 2000, message = "Bahan minimal 5 karakter dan maksimal 2000 karakter")
    private String bahan;
    @NotBlank(message = "Cara membuat tidak boleh kosong")
    @Size(min = 5, max = 3000, message = "Cara membuat minimal 5 karakter dan maksimal 3000 karakter")
    private String caraMembuat;
    @NotBlank(message = "User id tidak boleh kosong")
    private String userId;
    private BigDecimal harga;
    private Boolean isPublic;

}
