package com.kerupuksda.kerupuksdawebapi.utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@NoArgsConstructor
public class AppUtils {

    public static String formatDateTime(LocalDateTime time) {
        String formatDateTime = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatDateTime);
        return ObjectUtils.isEmpty(time) ? "" : time.format(dateTimeFormatter);
    }
}
