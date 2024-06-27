/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.utils.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalTime;

public class LocalTimeConverter extends BidirectionalConverter<LocalTime, LocalTime> {
    @Override
    public LocalTime convertTo(LocalTime localTime, Type<LocalTime> type, MappingContext mappingContext) {
        return LocalTime.from(localTime);
    }

    @Override
    public LocalTime convertFrom(LocalTime localTime, Type<LocalTime> type, MappingContext mappingContext) {
        return LocalTime.from(localTime);
    }
}
