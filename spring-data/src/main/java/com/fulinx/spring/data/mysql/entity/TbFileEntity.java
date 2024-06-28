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
 * File Table
 * </p>
 *
 * @author fulinx
 * @since 2024-06-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_file")
@Schema(name = "TbFileEntity", description = "File Table")
public class TbFileEntity extends Model<TbFileEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "File Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "Original File Name")
    private String originalFileName;

    @Schema(description = "File Name")
    private String fileName;

    @Schema(description = "File Content Type")
    private String fileContentType;

    @Schema(description = "File Extension Name")
    private String fileExtensionName;

    @Schema(description = "Path")
    private String path;

    @Schema(description = "File Url")
    private String fileUrl;

    @Schema(description = "sha256")
    private String sha256;

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
