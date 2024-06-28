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
 * System User Profile Table
 * </p>
 *
 * @author fulinx
 * @since 2024-06-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_system_user_profile")
@Schema(name = "TbSystemUserProfileEntity", description = "System User Profile Table")
public class TbSystemUserProfileEntity extends Model<TbSystemUserProfileEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "System User Profile Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "User Id")
    private Integer systemUserId;

    @Schema(description = "First Name")
    private String firstName;

    @Schema(description = "Last Name")
    private String lastName;

    @Schema(description = "Gender, 1 - Male, 2 - Female")
    private Integer gender;

    @Schema(description = "Telephone")
    private String telephone;

    @Schema(description = "Post")
    private String post;

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

    public static final String SYSTEM_USER_ID = "system_user_id";

    public static final String FIRST_NAME = "first_name";

    public static final String LAST_NAME = "last_name";

    public static final String GENDER = "gender";

    public static final String TELEPHONE = "telephone";

    public static final String POST = "post";

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
