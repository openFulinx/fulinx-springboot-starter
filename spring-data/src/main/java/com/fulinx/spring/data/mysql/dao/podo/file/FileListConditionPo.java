/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.file;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class FileListConditionPo implements Serializable {

    @Serial
    private static final long serialVersionUID = -467672439017601372L;

    @Schema(description = "File Id")
    private Integer id;

    @Schema(description = "Original File Name")
    private String originalFileName;

    @Schema(description = "File Name")
    private String fileName;

    @Schema(description = "Soft Delete Flag")
    @TableLogic
    private Integer isDelete;

}
