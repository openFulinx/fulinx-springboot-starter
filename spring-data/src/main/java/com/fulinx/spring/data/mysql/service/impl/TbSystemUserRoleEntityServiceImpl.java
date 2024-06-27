package com.fulinx.spring.data.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fulinx.spring.data.mysql.entity.TbSystemUserRoleEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbSystemUserRoleMapper;
import com.fulinx.spring.data.mysql.service.TbSystemUserRoleEntityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联关系表 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Service
public class TbSystemUserRoleEntityServiceImpl extends ServiceImpl<TbSystemUserRoleMapper, TbSystemUserRoleEntity> implements TbSystemUserRoleEntityService {

}
