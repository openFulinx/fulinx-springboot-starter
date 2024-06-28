package com.fulinx.spring.data.mysql.service.impl;

import com.fulinx.spring.data.mysql.entity.TbUserProfileEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbUserProfileMapper;
import com.fulinx.spring.data.mysql.service.TbUserProfileEntityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User Profile Table 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-28
 */
@Service
public class TbUserProfileEntityServiceImpl extends ServiceImpl<TbUserProfileMapper, TbUserProfileEntity> implements TbUserProfileEntityService {

}
