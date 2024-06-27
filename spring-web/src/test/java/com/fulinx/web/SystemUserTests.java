package com.fulinx.web;

import com.fulinx.spring.data.mysql.entity.TbSystemUserEntity;
import com.fulinx.spring.data.mysql.service.TbSystemUserEntityService;
import com.fulinx.spring.web.SpringWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(classes = SpringWebApplication.class)
@Slf4j
@ComponentScan({"com.fulinx"})
@MapperScan({"com.fulinx.spring.data.mysql.*.mapper"})
public class SystemUserTests {

    private final TbSystemUserEntityService tbSystemUserEntityService;

    @Autowired
    public SystemUserTests(TbSystemUserEntityService tbSystemUserEntityService) {
        this.tbSystemUserEntityService = tbSystemUserEntityService;
    }

    @Test
    void addUserTest() {
        // 新建用户
        String salt = String.valueOf(System.currentTimeMillis());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptPassword = bCryptPasswordEncoder.encode(StringUtils.join("lisi", salt));
        TbSystemUserEntity tbSystemUserEntity = new TbSystemUserEntity();
        tbSystemUserEntity.setUsername("lisi");
        tbSystemUserEntity.setEmail("admin@example.com");
        tbSystemUserEntity.setPassword(encryptPassword);
        tbSystemUserEntity.setSalt(salt);
        tbSystemUserEntity.setUserType(9999);
        tbSystemUserEntity.setStatus(1);
        tbSystemUserEntityService.save(tbSystemUserEntity);
    }
}
