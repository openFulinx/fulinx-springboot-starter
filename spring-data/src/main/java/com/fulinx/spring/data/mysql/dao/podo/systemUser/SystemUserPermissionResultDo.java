/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.systemUser;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fulinx.spring.data.mysql.entity.TbPermissionEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户角色权限表
 * </p>
 *
 * @author minong
 * @since 2023-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_permissions")
@Schema(description = "用户角色权限表")
public class SystemUserPermissionResultDo extends Model<TbPermissionEntity> {

    @Serial
    private static final long serialVersionUID = 2230454466162552723L;

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "权限名称")
    private String permissionName;

    @Parameter(name = "权限父级ID")
    private Integer permissionParentId;

    @Parameter(name = "权限唯一标识符")
    private String permissionCode;

    @Parameter(name = "权限类型：1. 节点 2. 分支")
    private Integer permissionType;

    @Parameter(name = "权限描述")
    private String permissionDescription;

    @Parameter(name = "子权限")
    private List<SystemUserPermissionResultDo> children;

    @Parameter(name = "软删除标识")
    private Integer isDelete;

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
