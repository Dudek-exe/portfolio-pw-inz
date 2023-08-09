package com.dudzinski.portfolio.infrastructure.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {

    public static Pageable preparePageable(int start, int end, String sort, String order) {
        int size = end - start;
        int pageNumber = ((end / size) - 1);

        return PageRequest.of(pageNumber, size, Sort.Direction.fromString(order), sort);
    }
}
