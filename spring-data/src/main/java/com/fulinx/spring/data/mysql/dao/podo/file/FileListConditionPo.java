/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.file;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户账户-查询条件实体
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FileListConditionPo implements Serializable {

    @Serial
    private static final long serialVersionUID = -467672439017601372L;

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "文件名称")
    private String fileName;

    @Parameter(name = "软删除标识")
    @TableLogic
    private Integer isDelete;

}
