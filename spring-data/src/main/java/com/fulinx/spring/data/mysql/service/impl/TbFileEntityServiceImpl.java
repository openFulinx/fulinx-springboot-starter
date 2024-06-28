package com.fulinx.spring.data.mysql.service.impl;

import com.fulinx.spring.data.mysql.entity.TbFileEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbFileMapper;
import com.fulinx.spring.data.mysql.service.TbFileEntityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * File Table 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-28
 */
@Service
public class TbFileEntityServiceImpl extends ServiceImpl<TbFileMapper, TbFileEntity> implements TbFileEntityService {

}
