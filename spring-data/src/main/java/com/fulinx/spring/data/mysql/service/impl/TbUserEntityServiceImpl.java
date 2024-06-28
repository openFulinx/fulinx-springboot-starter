package com.fulinx.spring.data.mysql.service.impl;

import com.fulinx.spring.data.mysql.entity.TbUserEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbUserMapper;
import com.fulinx.spring.data.mysql.service.TbUserEntityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User Table 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-28
 */
@Service
public class TbUserEntityServiceImpl extends ServiceImpl<TbUserMapper, TbUserEntity> implements TbUserEntityService {

}
