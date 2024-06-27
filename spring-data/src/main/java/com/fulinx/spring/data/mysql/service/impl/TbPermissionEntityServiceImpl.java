package com.fulinx.spring.data.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fulinx.spring.data.mysql.entity.TbPermissionEntity;
import com.fulinx.spring.data.mysql.entity.mapper.TbPermissionMapper;
import com.fulinx.spring.data.mysql.service.TbPermissionEntityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色权限表 服务实现类
 * </p>
 *
 * @author fulinx
 * @since 2024-06-22
 */
@Service
public class TbPermissionEntityServiceImpl extends ServiceImpl<TbPermissionMapper, TbPermissionEntity> implements TbPermissionEntityService {

}
