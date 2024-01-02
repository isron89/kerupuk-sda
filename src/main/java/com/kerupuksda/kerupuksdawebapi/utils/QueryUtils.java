package com.kerupuksda.kerupuksdawebapi.utils;

import com.kerupuksda.kerupuksdawebapi.models.request.BasePaginationRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.Locale;

public class QueryUtils {

    private QueryUtils() {

    }

    public static String queryLikeFormatter(String field) {
        if (StringUtils.isEmpty(field)) {
            return "%%";
        }
        return "%" + field + "%";
    }

    public static <T extends BasePaginationRequest> Pageable unsafePaging(T input, Integer defaultPageSize, Integer defaultPageNumber) {
        Sort.Direction order = "ASC".equalsIgnoreCase(input.getSortType()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        JpaSort sort = JpaSort.unsafe(order, input.getSortBy());
        Integer pageSize = input.getPageSize() != null ? input.getPageSize() : defaultPageSize;
        return PageRequest.of(PageableUtil.getPageNumber(input.getPageNumber(), defaultPageNumber), pageSize, sort);
    }

    public static String searchNameLowerCase(String searchNameFromRequest) {
        if (StringUtils.isNotEmpty(searchNameFromRequest)) {
            searchNameFromRequest = searchNameFromRequest.toLowerCase(Locale.ROOT);
        }

        return searchNameFromRequest;
    }
}
