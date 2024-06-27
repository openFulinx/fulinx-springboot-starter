package com.fulinx.spring.data.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fulinx.spring.data.mysql.entity.TbSystemUserProfileEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbSystemUserProfileMapper;
import com.fulinx.spring.data.mysql.service.TbSystemUserProfileEntityService;
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
public class TbSystemUserProfileEntityServiceImpl extends ServiceImpl<TbSystemUserProfileMapper, TbSystemUserProfileEntity> implements TbSystemUserProfileEntityService {

}
