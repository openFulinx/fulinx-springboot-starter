/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.dto;

import com.fulinx.spring.data.mysql.entity.TbUserProfileEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserProfileQueryResultDto extends TbUserProfileEntity {
    @Serial
    private static final long serialVersionUID = 8847395619138615807L;
}
