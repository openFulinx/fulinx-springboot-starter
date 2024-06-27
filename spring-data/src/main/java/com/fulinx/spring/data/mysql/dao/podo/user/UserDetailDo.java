/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.user;

import com.fulinx.spring.data.mysql.entity.TbFileEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetailDo implements Serializable {

    private static final long serialVersionUID = 3670541294069365380L;

    @Parameter(name = "User Id")
    private Integer userId;

    @Parameter(name = "Nick Name")
    private String nickname;

    @Parameter(name = "Avatar New File Vo")
    private TbFileEntity avatarFileVo;
}
