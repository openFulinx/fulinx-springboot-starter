package com.fulinx.spring.data.mysql.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * User Table
 * </p>
 *
 * @author fulinx
 * @since 2024-06-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_user")
@Schema(name = "TbUserEntity", description = "User Table")
public class TbUserEntity extends Model<TbUserEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "User Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Password")
    private String password;

    @Schema(description = "Salt")
    private String salt;

    @Schema(description = "Email Verification Status: 0 - Unverified, 1 - Verified")
    private Integer isEmailVerify;

    @Schema(description = "User Type: 1 - Regular User, 9999 - Super Administrator")
    private Integer userType;

    @Schema(description = "Status: 1 - Enabled, 9 - Disabled")
    private Integer status;

    @Schema(description = "Soft Delete Flag")
    @TableLogic
    private Integer isDelete;

    @Schema(description = "Remark")
    private String remark;

    @Schema(description = "Record Version")
    @Version
    private Integer recordVersion;

    @Schema(description = "Record Create Name")
    @TableField(fill = FieldFill.INSERT)
    private String recordCreateName;

    @Schema(description = "Record Update Name")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String recordUpdateName;

    @Schema(description = "Record Create Time")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime recordCreateTime;

    @Schema(description = "Record Update Time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime recordUpdateTime;

    public static final String ID = "id";

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String IS_EMAIL_VERIFY = "is_email_verify";

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
