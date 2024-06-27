package com.fulinx.spring.data.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fulinx.spring.data.mysql.entity.TbRoleEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbRoleMapper;
import com.fulinx.spring.data.mysql.service.TbRoleEntityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Service
public class TbRoleEntityServiceImpl extends ServiceImpl<TbRoleMapper, TbRoleEntity> implements TbRoleEntityService {

}
