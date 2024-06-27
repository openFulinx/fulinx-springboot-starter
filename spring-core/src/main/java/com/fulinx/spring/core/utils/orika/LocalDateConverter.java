/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.utils.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;

public class LocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {
    @Override
    public LocalDate convertTo(LocalDate localDate, Type<LocalDate> type, MappingContext mappingContext) {
        return LocalDate.from(localDate);
    }

    @Override
    public LocalDate convertFrom(LocalDate localDate, Type<LocalDate> type, MappingContext mappingContext) {
        return LocalDate.from(localDate);
    }
}
