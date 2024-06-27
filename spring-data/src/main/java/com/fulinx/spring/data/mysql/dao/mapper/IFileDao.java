/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.mapper;

import com.fulinx.spring.data.mysql.dao.podo.file.FileListConditionPo;
import com.fulinx.spring.data.mysql.dao.podo.file.FileListResultDo;
import com.fulinx.spring.data.mysql.entity.TbFileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Mapper
public interface IFileDao {

    List<FileListResultDo> list(FileListConditionPo fileListConditionPo);

    TbFileEntity lockById(Serializable id);

    List<TbFileEntity> lockByIds(Collection<? extends Serializable> idList);
}
