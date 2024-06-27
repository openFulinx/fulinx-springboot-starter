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
 * 文件表
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_file")
@Schema(name = "TbFileEntity", description = "文件表")
public class TbFileEntity extends Model<TbFileEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "原始文件名称")
    private String originalFileName;

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "文件类型")
    private String fileContentType;

    @Schema(description = "文件扩展名")
    private String fileExtensionName;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "URL地址")
    private String fileUrl;

    @Schema(description = "sha256")
    private String sha256;

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

    public static final String USER_ID = "user_id";

    public static final String ORIGINAL_FILE_NAME = "original_file_name";

    public static final String FILE_NAME = "file_name";

    public static final String FILE_CONTENT_TYPE = "file_content_type";

    public static final String FILE_EXTENSION_NAME = "file_extension_name";

    public static final String PATH = "path";

    public static final String FILE_URL = "file_url";

    public static final String SHA256 = "sha256";

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
