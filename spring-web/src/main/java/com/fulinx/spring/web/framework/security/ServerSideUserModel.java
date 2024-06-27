
package com.fulinx.spring.web.framework.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fulinx.spring.service.system.user.dto.SystemUserResultDto;
import com.fulinx.spring.web.framework.jackson.ServerSideUserModelJsonDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonDeserialize(using = ServerSideUserModelJsonDeserializer.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ServerSideUserModel extends SystemUserResultDto {

    private static final long serialVersionUID = -1941301182255711411L;
}
