package com.fulinx.spring.data.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fulinx.spring.data.mysql.entity.TbUserEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbUserMapper;
import com.fulinx.spring.data.mysql.service.TbUserEntityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Service
public class TbUserEntityServiceImpl extends ServiceImpl<TbUserMapper, TbUserEntity> implements TbUserEntityService {

}
