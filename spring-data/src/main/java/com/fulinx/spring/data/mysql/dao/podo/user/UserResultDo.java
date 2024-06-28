package com.fulinx.spring.data.mysql.dao.podo.user;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Telephone")
    private String telephone;
}
