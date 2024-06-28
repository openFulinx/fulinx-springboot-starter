/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.file;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "File Id")
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
    private String recordCreateName;

    @Schema(description = "Record Update Name")
    private String recordUpdateName;

    @Schema(description = "Record Create Time")
    private LocalDateTime recordCreateTime;

    @Schema(description = "Record Update Time")
    private LocalDateTime recordUpdateTime;
}
