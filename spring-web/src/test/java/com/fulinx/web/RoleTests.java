package com.fulinx.web;

import com.fulinx.spring.data.mysql.entity.TbPermissionEntity;
import com.fulinx.spring.data.mysql.entity.TbRoleEntity;
import com.fulinx.spring.data.mysql.entity.TbRolePermissionRelationEntity;
import com.fulinx.spring.data.mysql.service.TbPermissionEntityService;
import com.fulinx.spring.data.mysql.service.TbRoleEntityService;
import com.fulinx.spring.data.mysql.service.TbRolePermissionRelationEntityService;
import com.fulinx.spring.web.SpringWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootTest(classes = SpringWebApplication.class)
@Slf4j
@ComponentScan({"com.fulinx"})
@MapperScan({"com.fulinx.spring.data.mysql.*.mapper"})
public class RoleTests {

    private final TbRoleEntityService tbRoleEntityService;

    private final TbPermissionEntityService tbPermissionEntityService;

    private final TbRolePermissionRelationEntityService tbRolePermissionRelationEntityService;

    @Autowired
    public RoleTests(TbRoleEntityService tbRoleEntityService, TbPermissionEntityService tbPermissionEntityService, TbRolePermissionRelationEntityService tbRolePermissionRelationEntityService) {
        this.tbRoleEntityService = tbRoleEntityService;
        this.tbPermissionEntityService = tbPermissionEntityService;
        this.tbRolePermissionRelationEntityService = tbRolePermissionRelationEntityService;
    }


    @Test
    void roleTest() {
        TbRoleEntity tbRoleEntity = new TbRoleEntity();
        String roleName = "Administrator";
        List<TbRoleEntity> list = tbRoleEntityService.lambdaQuery().eq(TbRoleEntity::getRoleName, roleName).list();
        if (list.isEmpty()) {
            tbRoleEntity.setRoleName(roleName);
            tbRoleEntityService.save(tbRoleEntity);
        }
    }

    @Test
    void roleAdministratorTest() {
        String roleName = "Administrator";
        List<TbRoleEntity> list = tbRoleEntityService.lambdaQuery().eq(TbRoleEntity::getRoleName, roleName).list();
        if (!list.isEmpty()) {
            TbRoleEntity tbRoleEntity = list.get(0);
            List<TbPermissionEntity> tbPermissionEntityList = tbPermissionEntityService.list();
            tbPermissionEntityList.forEach(tbPermissionEntity -> {
                TbRolePermissionRelationEntity tbRolePermissionRelationEntity = new TbRolePermissionRelationEntity();
                tbRolePermissionRelationEntity.setRoleId(tbRoleEntity.getId());
                tbRolePermissionRelationEntity.setPermissionId(tbPermissionEntity.getId());
                tbRolePermissionRelationEntityService.save(tbRolePermissionRelationEntity);
            });
        }
    }
}
