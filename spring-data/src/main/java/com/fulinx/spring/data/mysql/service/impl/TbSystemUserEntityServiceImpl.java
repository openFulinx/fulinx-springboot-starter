package com.fulinx.spring.data.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fulinx.spring.data.mysql.entity.TbSystemUserEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbSystemUserMapper;
import com.fulinx.spring.data.mysql.service.TbSystemUserEntityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * System User Table 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Service
public class TbSystemUserEntityServiceImpl extends ServiceImpl<TbSystemUserMapper, TbSystemUserEntity> implements TbSystemUserEntityService {

}
