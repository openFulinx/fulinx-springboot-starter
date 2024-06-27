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
 * 用户表
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_user")
@Schema(name = "TbUserEntity", description = "用户表")
public class TbUserEntity extends Model<TbUserEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "盐")
    private String salt;

    @Schema(description = "邮箱是否验证，0：未验证，1：已验证")
    private Integer isVerify;

    @Schema(description = "用户类型:1,普通用户，9999超级管理员")
    private Integer userType;

    @Schema(description = "状态: 1: 启用,  9: 禁用, ")
    private Integer status;

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

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String IS_VERIFY = "is_verify";

    public static final String USER_TYPE = "user_type";

    public static final String STATUS = "status";

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
