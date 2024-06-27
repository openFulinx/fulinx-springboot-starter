/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.systemUser;

import com.fulinx.spring.data.mysql.entity.TbFileEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserListResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = -3097863283384102285L;

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "昵称")
    private String nickname;

    @Parameter(name = "用户头像")
    private TbFileEntity userAvatar;

    @Parameter(name = "用户头像状态")
    private Integer userAvatarStatus;

    @Parameter(name = "用户角色列表")
    private String roleIds;

    @Parameter(name = "姓名")
    private String name;

    @Parameter(name = "姓")
    private String firstName;

    @Parameter(name = "名")
    private String lastName;

    @Parameter(name = "生日")
    private Integer birthday;

    @Parameter(name = "性别")
    private Integer gender;

    @Parameter(name = "公司名称")
    private String company;

    @Parameter(name = "手机")
    private String telephone;

    @Parameter(name = "职务")
    private String post;

    @Parameter(name = "个人简介")
    private String personalInformation;

    @Parameter(name = "状态: 0: 禁用, 1: 启用")
    private Integer status;

    @Parameter(name = "备注")
    private String remark;

    @Parameter(name = "记录版本")
    private Integer recordVersion;

    @Parameter(name = "创建者")
    private String recordCreateName;

    @Parameter(name = "更新者")
    private String recordUpdateName;

    @Parameter(name = "创建时间")
    private LocalDateTime recordCreateTime;

    @Parameter(name = "更新时间")
    private LocalDateTime recordUpdateTime;
}
