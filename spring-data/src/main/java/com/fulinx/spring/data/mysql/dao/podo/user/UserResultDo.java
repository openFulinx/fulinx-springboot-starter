package com.fulinx.spring.data.mysql.dao.podo.user;

import com.fulinx.spring.data.mysql.entity.TbFileEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = 615235140817012220L;

    @Parameter(name = "User Id")
    private Integer userId;

    @Parameter(name = "Nick Name")
    private String nickname;

    @Parameter(name = "手机号码")
    private String telephone;

    @Parameter(name = "Avatar New File Vo")
    private TbFileEntity avatarFileVo;

    @Parameter(name = "是否关注")
    private Boolean isFollowed;
}
