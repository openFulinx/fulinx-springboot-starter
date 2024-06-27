package com.fulinx.spring.web.framework.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fulinx.spring.service.user.dto.UserResultDto;
import com.fulinx.spring.web.framework.jackson.ClientSideUserModelJsonDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonDeserialize(using = ClientSideUserModelJsonDeserializer.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientSideUserModel extends UserResultDto {
    private static final long serialVersionUID = -2544632939947466573L;
}
