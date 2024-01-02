package com.kerupuksda.kerupuksdawebapi.models.response.products;

import com.kerupuksda.kerupuksdawebapi.models.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse extends BaseResponse {

    private String id;
    private String nama;
    private String deskripsi;
    private String bahan;
    private String caraMembuat;
    private BigDecimal harga;
    private Boolean isPublic;
    private String tanggalBuat;
    private String pembuat;

}
