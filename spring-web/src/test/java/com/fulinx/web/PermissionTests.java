package com.fulinx.web;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.fulinx.spring.data.mysql.dao.podo.permission.PermissionListResultDo;
import com.fulinx.spring.data.mysql.entity.TbPermissionEntity;
import com.fulinx.spring.data.mysql.service.TbPermissionEntityService;
import com.fulinx.spring.web.SpringWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = SpringWebApplication.class)
@Slf4j
@ComponentScan({"com.fulinx"})
@MapperScan({"com.fulinx.spring.data.mysql.*.mapper"})
public class PermissionTests {

    private final TbPermissionEntityService tbPermissionEntityService;

    @Autowired
    public PermissionTests(TbPermissionEntityService tbPermissionEntityService) {
        this.tbPermissionEntityService = tbPermissionEntityService;
    }


    @Test
    void test() {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("data/permissions.json");
        if (resourceAsStream != null) {
            String content = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
            List<PermissionListResultDo> permissionListResultDoList = JSON.parseObject(content, new TypeReference<List<PermissionListResultDo>>() {
            });
            permissionListResultDoList.forEach(systemUserRolePermissionResultDo -> {
                savePermission(systemUserRolePermissionResultDo);
            });
        }
    }

    private TbPermissionEntity savePermission(PermissionListResultDo systemUserRolePermissionResultDo) {
        TbPermissionEntity tbPermissionEntity = new TbPermissionEntity();
        tbPermissionEntity.setPermissionParentId(systemUserRolePermissionResultDo.getPermissionParentId());
        tbPermissionEntity.setPermissionCode(systemUserRolePermissionResultDo.getPermissionCode());
        tbPermissionEntity.setPermissionType(systemUserRolePermissionResultDo.getPermissionType());
        tbPermissionEntity.setIsDelete(systemUserRolePermissionResultDo.getIsDelete());
        tbPermissionEntity.setRemark(systemUserRolePermissionResultDo.getRemark());
        tbPermissionEntity.setRecordVersion(systemUserRolePermissionResultDo.getRecordVersion());
        tbPermissionEntity.setRecordCreateName(systemUserRolePermissionResultDo.getRecordCreateName());
        tbPermissionEntity.setRecordUpdateName(systemUserRolePermissionResultDo.getRecordUpdateName());
        tbPermissionEntity.setRecordCreateTime(systemUserRolePermissionResultDo.getRecordCreateTime());
        tbPermissionEntity.setRecordUpdateTime(systemUserRolePermissionResultDo.getRecordUpdateTime());
        tbPermissionEntityService.save(tbPermissionEntity);
        if (systemUserRolePermissionResultDo.getChildren() != null) {
            systemUserRolePermissionResultDo.getChildren().forEach(child -> {
                child.setPermissionParentId(tbPermissionEntity.getId());
                savePermission(child);
            });
        }
        return tbPermissionEntity;
    }
}
