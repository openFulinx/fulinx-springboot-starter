package com.fulinx.web;

import com.fulinx.spring.data.mysql.entity.TbSystemUserEntity;
import com.fulinx.spring.data.mysql.entity.TbSystemUserProfileEntity;
import com.fulinx.spring.data.mysql.entity.TbSystemUserRoleEntity;
import com.fulinx.spring.data.mysql.service.TbSystemUserEntityService;
import com.fulinx.spring.data.mysql.service.TbSystemUserProfileEntityService;
import com.fulinx.spring.data.mysql.service.TbSystemUserRoleEntityService;
import com.fulinx.spring.web.SpringWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest(classes = SpringWebApplication.class)
@Slf4j
@ComponentScan({"com.fulinx"})
@MapperScan({"com.fulinx.spring.data.mysql.*.mapper"})
public class SystemUserTests {

    private final TbSystemUserEntityService tbSystemUserEntityService;

    private final TbSystemUserProfileEntityService tbSystemUserProfileEntityService;

    private final TbSystemUserRoleEntityService tbSystemUserRoleEntityService;

    @Autowired
    public SystemUserTests(TbSystemUserEntityService tbSystemUserEntityService, TbSystemUserProfileEntityService tbSystemUserProfileEntityService, TbSystemUserRoleEntityService tbSystemUserRoleEntityService) {
        this.tbSystemUserEntityService = tbSystemUserEntityService;
        this.tbSystemUserProfileEntityService = tbSystemUserProfileEntityService;
        this.tbSystemUserRoleEntityService = tbSystemUserRoleEntityService;
    }

    @Test
    void addUserTest() {
        List<TbSystemUserEntity> list = tbSystemUserEntityService.lambdaQuery().eq(TbSystemUserEntity::getUsername, "admin").list();
        if (list.isEmpty()) {
            // Create User
            String salt = String.valueOf(System.currentTimeMillis());
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encryptPassword = bCryptPasswordEncoder.encode(StringUtils.join("admin", salt));
            TbSystemUserEntity tbSystemUserEntity = new TbSystemUserEntity();
            tbSystemUserEntity.setUsername("admin");
            tbSystemUserEntity.setEmail("admin@example.com");
            tbSystemUserEntity.setPassword(encryptPassword);
            tbSystemUserEntity.setSalt(salt);
            tbSystemUserEntity.setUserType(9999);
            tbSystemUserEntity.setStatus(1);
            tbSystemUserEntityService.save(tbSystemUserEntity);
            // Create User Profile
            TbSystemUserProfileEntity tbSystemUserProfileEntity = new TbSystemUserProfileEntity();
            tbSystemUserProfileEntity.setSystemUserId(tbSystemUserEntity.getId());
            tbSystemUserProfileEntity.setFirstName("admin");
            tbSystemUserProfileEntity.setLastName("admin");
            tbSystemUserProfileEntityService.save(tbSystemUserProfileEntity);
            // Create User Role
            TbSystemUserRoleEntity tbSystemUserRoleEntity = new TbSystemUserRoleEntity();
            tbSystemUserRoleEntity.setSystemUserId(tbSystemUserEntity.getId());
            tbSystemUserRoleEntity.setRoleId(1);
            tbSystemUserRoleEntityService.save(tbSystemUserRoleEntity);
        }
    }
}
