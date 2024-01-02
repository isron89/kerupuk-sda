package com.kerupuksda.kerupuksdawebapi.utils;

import com.kerupuksda.kerupuksdawebapi.models.request.BasePaginationRequest;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseSearchRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;

@Slf4j
public final class PageableUtil {

    private static final String ASC_VALUE = "ASC";

    private static final String CAMEL_CASE_REGX = "^[A-Za-z0-9_.]+$";

    private PageableUtil() {

    }

    public static PageRequest createPageRequest(BasePaginationRequest req, Integer defaultPageSize, Integer defaultPageNumber, String defaultSortBy, String defaultSortType) {
    	log.info("createPageRequest -> {}", req);
    	
    	if (req.getSortBy() != null && req.getSortBy().contains("_")) {
            req.setSortBy(LOWER_UNDERSCORE.to(LOWER_CAMEL, req.getSortBy()));
        }

        if (defaultSortBy.contains("_")) {
            defaultSortBy = LOWER_UNDERSCORE.to(LOWER_CAMEL, defaultSortBy);
        }

        if (req.getPageSize() != null) {
            defaultPageSize = req.getPageSize();
        }

        if (req.getPageNumber() != null) {
            defaultPageNumber = req.getPageNumber();
        }


        if (req.getSortBy() != null) {
            defaultSortBy = req.getSortBy();
        }

        if (req.getSortType() != null) {
            defaultSortType = req.getSortType();
        }

        defaultPageNumber = getPageNumber(req.getPageNumber(), defaultPageNumber);

        //paging in jpa starts from 0
        return PageRequest.of(defaultPageNumber, defaultPageSize, ASC_VALUE.equalsIgnoreCase(defaultSortType) ? Direction.ASC : Direction.DESC, defaultSortBy);

    }

    /**
     * Earlier version of createPageRequestNative()
     * Menggunakan BasePaginationRequest sebagai container utama
     * @param req -> BasePaginationRequest
     */
    public static PageRequest createPageRequestNative(BasePaginationRequest req, Integer defaultPageSize, Integer defaultPageNumber, String defaultSortBy, String defaultSortType) {

        if (req.getPageSize() != null) {
            defaultPageSize = req.getPageSize();
        }

        if (req.getSortBy() != null && req.getSortBy().matches(CAMEL_CASE_REGX)) {
            req.setSortBy(LOWER_CAMEL.to(LOWER_UNDERSCORE, req.getSortBy()));
        }

        if (defaultSortBy.matches(CAMEL_CASE_REGX)) {
            defaultSortBy = LOWER_CAMEL.to(LOWER_UNDERSCORE, defaultSortBy);
        }

        if (req.getSortBy() != null) {
            defaultSortBy = req.getSortBy();
        }

        if (req.getSortType() != null) {
            defaultSortType = req.getSortType();
        }

        defaultPageNumber = getPageNumber(req.getPageNumber(), defaultPageNumber);
        return PageRequest.of(defaultPageNumber, defaultPageSize, ASC_VALUE.equalsIgnoreCase(defaultSortType) ? Direction.ASC : Direction.DESC, defaultSortBy);
    }



    public static Integer getPageNumber(Integer reqPageNumber, Integer defaultPageNumber) {
        if (reqPageNumber != null) {
            if (reqPageNumber > 0) {
                return reqPageNumber - 1;
            } else {
                log.warn("Page index must not be less than one original value {}, default value {} is used instead.", reqPageNumber, defaultPageNumber + 1);
            }
        }
        return defaultPageNumber;
    }

    public static Integer getPageSize(Integer reqPageSize, Integer defaultPageSize){
        if (ObjectUtils.isNotEmpty(reqPageSize)) return reqPageSize;
        else return defaultPageSize;
    }

    /**
     * Second version of createPageRequestNative()
     * Menggunakan BaseSearchRequest sebagai container utama.
     * Assign value default untuk status dan isDeleted
     * @param req -> BaseSearchRequest
     */
    public static PageRequest createPageRequestNative(BaseSearchRequest req, Integer defaultPageSize, Integer defaultPageNumber, String defaultSortBy, String defaultSortType) {
        updateStatusAndIsDelete(req);
        return createPageRequestNative((BasePaginationRequest) req, defaultPageSize, defaultPageNumber, defaultSortBy, defaultSortType);
    }

    /**
     * Third version of createPageRequestNative()
     * Menggunakan BaseSearchRequest sebagai container utama.
     * Pengecekan filed sortBy apakah bertipe time.
     * Jika field tidak bertipe time, maka order harus dilakukan
     * menggunakan ignore case.
     * Contoh list -> Ayam, singa, anjing, kucing, Kambing
     * result -> anjing, Ayam, Kambing, kucing, singa
     * Assign value default untuk status dan isDeleted
     * @param req -> BaseSearchRequest
     * @param entityClass
     * @return
     */
    public static PageRequest createPageRequestNative(BaseSearchRequest req,
                                                      Integer defaultPageSize,
                                                      Integer defaultPageNumber,
                                                      String defaultSortBy,
                                                      String defaultSortType,
                                                      Class<?> entityClass) {
        boolean isFieldTime = isFieldTypeTime(entityClass, req.getSortBy());
        if (!isFieldTime && StringUtils.isNotEmpty(req.getSortBy())) {
            updateStatusAndIsDelete(req);
            if (req.getSortBy() != null && req.getSortBy().matches(CAMEL_CASE_REGX)) {
                req.setSortBy(LOWER_CAMEL.to(LOWER_UNDERSCORE, req.getSortBy()));
            }
            Integer pageSize = req.getPageSize() != null ? req.getPageSize() : defaultPageSize;
            Sort.Order order = new Sort.Order(ASC_VALUE.equalsIgnoreCase(req.getSortType()) ? Direction.ASC : Direction.DESC, req.getSortBy()).ignoreCase();
            return PageRequest.of(getPageNumber(req.getPageNumber(), defaultPageNumber), pageSize, Sort.by(order));
        } else {
            return PageableUtil.createPageRequestNative(req, defaultPageSize, defaultPageNumber, defaultSortBy, defaultSortType);
        }
    }

    public static void updateStatusAndIsDelete(BaseSearchRequest req) {
        if (req.getStatus() == null) {
            req.setStatus(1);
        }
        if (req.getIsDeleted() == null) {
            req.setIsDeleted(0);
        }
    }

    private static boolean isFieldTypeTime(Class<?> anyClass, String fieldName) {
        try {
            if (StringUtils.isNotEmpty(fieldName)) {
                Field field = anyClass.getDeclaredField(fieldName);
                if (!String.class.isAssignableFrom(field.getType())) {
                    return true;
                }
            }
        } catch (NoSuchFieldException ignored) {
            Class<?> superclass = anyClass.getSuperclass();
            if (superclass != null) {
                return isFieldTypeTime(superclass, fieldName);
            }
            // ignored
            log.warn("field {} is not found in {}.class", fieldName, anyClass.getName());
        }
        return false;
    }

    public static <T> Pagination pageToPagination(Page<T> page) {
        return Pagination.builder()
                .pageSize(page.getSize())

                //paging in jpa starts from 0, so need to plus one for back to user page number
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalRecords(page.getTotalElements())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .build();
    }

    public static <T> Page<T> contentsToPageConversion(List<T> contents, BasePaginationRequest request, int defaultPageNumber,
                                                       int defaultPageSize) {
        Pageable paging = PageRequest.of(getPageNumber(request.getPageNumber(), defaultPageNumber),
                getPageSize(request.getPageSize(), defaultPageSize));
        int start = Math.min((int)paging.getOffset(), contents.size());
        int end = Math.min((start + paging.getPageSize()), contents.size());
        return new PageImpl<>(contents.subList(start, end), paging, contents.size());
    }

}
