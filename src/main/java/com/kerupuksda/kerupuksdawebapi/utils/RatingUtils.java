package com.kerupuksda.kerupuksdawebapi.utils;

import com.kerupuksda.kerupuksdawebapi.repository.ReactRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Data
@Slf4j
@Component
@RequiredArgsConstructor
public class RatingUtils {

    private final ReactRepository reactRepository;

    public String getRatingProduct(String productId) {
        Float rating =  reactRepository.getRatingProduct(productId);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        return df.format(rating);
    }

}
