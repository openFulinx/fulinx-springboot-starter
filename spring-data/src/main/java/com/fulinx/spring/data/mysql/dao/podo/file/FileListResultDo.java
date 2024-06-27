/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.file;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileListResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = -7026957750457725266L;

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "原始文件名称")
    private String originalFileName;

    @Parameter(name = "文件名称")
    private String fileName;

    @Parameter(name = "文件类型")
    private String fileContentType;

    @Parameter(name = "文件扩展名")
    private String fileExtensionName;

    @Parameter(name = "文件路径")
    private String path;

    @Parameter(name = "URL地址")
    private String fileUrl;

    @Parameter(name = "Soft Delete Flag")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @Parameter(name = "Remark")
    private String remark;

    @Parameter(name = "Record Version")
    @TableField("record_version")
    @Version
    private Integer recordVersion;

    @Parameter(name = "Record Create Name")
    @TableField(value = "record_create_name", fill = FieldFill.INSERT)
    private String recordCreateName;

    @Parameter(name = "Record Update Name")
    @TableField(value = "record_update_name", fill = FieldFill.INSERT_UPDATE)
    private String recordUpdateName;

    @Parameter(name = "Record Create Time")
    @TableField(value = "record_create_time", fill = FieldFill.INSERT)
    private LocalDateTime recordCreateTime;

    @Parameter(name = "Record Update Time")
    @TableField(value = "record_update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime recordUpdateTime;
}
