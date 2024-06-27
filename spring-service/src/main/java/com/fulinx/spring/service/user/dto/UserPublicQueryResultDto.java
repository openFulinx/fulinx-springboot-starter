/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user.dto;

import com.fulinx.spring.data.mysql.entity.TbFileEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserPublicQueryResultDto {

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "昵称")
    private String nickname;

    @Parameter(name = "个人简介")
    private String personalInformation;

    @Parameter(name = "是否关注")
    private Boolean isFollow;

    @Parameter(name = "用户头像")
    private TbFileEntity userAvatarVo;

    @Parameter(name = "关注数量")
    private Integer followingCount;

    @Parameter(name = "粉丝")
    private Integer followersCount;
}
