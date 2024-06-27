/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user.dto;

import com.fulinx.spring.data.mysql.entity.TbUserProfileEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfileQueryConditionDto extends TbUserProfileEntity {

    @Serial
    private static final long serialVersionUID = -5778004680606843518L;

}
