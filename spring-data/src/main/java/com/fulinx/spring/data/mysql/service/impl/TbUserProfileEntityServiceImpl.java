package com.fulinx.spring.data.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fulinx.spring.data.mysql.entity.TbUserProfileEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbUserProfileMapper;
import com.fulinx.spring.data.mysql.service.TbUserProfileEntityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户资料 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Service
public class TbUserProfileEntityServiceImpl extends ServiceImpl<TbUserProfileMapper, TbUserProfileEntity> implements TbUserProfileEntityService {

}
