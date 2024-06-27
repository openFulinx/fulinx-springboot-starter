/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.data.pagination;

import com.fulinx.spring.core.data.pagination.emums.PaginationMethodEnum;
import lombok.Builder;
import lombok.Getter;

@Builder
public class Pagination {

    @Getter
    private final PaginationMethodEnum paginationMethodEnum;

    @Getter
    private final int pageNumber;

    @Getter
    private final int pageSize;
}
