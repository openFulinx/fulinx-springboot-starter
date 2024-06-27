package com.fulinx.spring.data.mysql.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户角色权限表
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_permission")
@Schema(name = "TbPermissionEntity", description = "用户角色权限表")
public class TbPermissionEntity extends Model<TbPermissionEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "权限名称")
    private String permissionName;

    @Schema(description = "权限父级ID")
    private Integer permissionParentId;

    @Schema(description = "权限唯一标识符")
    private String permissionCode;

    @Schema(description = "权限类型：1. 节点 2. 分支")
    private Integer permissionType;

    @Schema(description = "权限描述")
    private String description;

    @Schema(description = "软删除标识")
    @TableLogic
    private Integer isDelete;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "记录版本")
    @Version
    private Integer recordVersion;

    @Schema(description = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String recordCreateName;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String recordUpdateName;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime recordCreateTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime recordUpdateTime;

    public static final String ID = "id";

    public static final String PERMISSION_NAME = "permission_name";

    public static final String PERMISSION_PARENT_ID = "permission_parent_id";

    public static final String PERMISSION_CODE = "permission_code";

    public static final String PERMISSION_TYPE = "permission_type";

    public static final String DESCRIPTION = "description";

    public static final String IS_DELETE = "is_delete";

    public static final String REMARK = "remark";

    public static final String RECORD_VERSION = "record_version";

    public static final String RECORD_CREATE_NAME = "record_create_name";

    public static final String RECORD_UPDATE_NAME = "record_update_name";

    public static final String RECORD_CREATE_TIME = "record_create_time";

    public static final String RECORD_UPDATE_TIME = "record_update_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
