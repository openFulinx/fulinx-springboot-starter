/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.jackson;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fulinx.spring.data.mysql.entity.TbUserEntity;
import com.fulinx.spring.data.mysql.entity.TbUserProfileEntity;
import com.fulinx.spring.web.framework.security.ClientSideUserModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientSideUserModelJsonDeserializer extends JsonDeserializer<ClientSideUserModel> {

    @Override
    public ClientSideUserModel deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        Integer userId = node.get("userId").asInt();
        TbUserEntity mesUsersEntity = new TbUserEntity();
        TbUserProfileEntity mesUserProfilesEntity = new TbUserProfileEntity();
        TbUserEntity usersEntity = mesUsersEntity.selectById(userId);
        // 取出用户资料信息
        LambdaQueryWrapper<TbUserProfileEntity> mesUserProfilesEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        mesUserProfilesEntityLambdaQueryWrapper.eq(TbUserProfileEntity::getUserId, userId);
        List<TbUserProfileEntity> mesUserProfilesEntities = mesUserProfilesEntity.selectList(mesUserProfilesEntityLambdaQueryWrapper);

        ClientSideUserModel clientSideUserModel = new ClientSideUserModel();
        clientSideUserModel.setOperatorName(usersEntity.getEmail());
        if (!mesUserProfilesEntities.isEmpty()) {
            TbUserProfileEntity userProfilesEntity = mesUserProfilesEntities.get(0);
            if (userProfilesEntity != null) {
                clientSideUserModel.setUserProfileId(userProfilesEntity.getId());
                clientSideUserModel.setFirstName(userProfilesEntity.getFirstName());
                clientSideUserModel.setLastName(userProfilesEntity.getLastName());
                clientSideUserModel.setGender(userProfilesEntity.getGender());
                clientSideUserModel.setPost(userProfilesEntity.getPost());
            }
        }
        clientSideUserModel.setUserId(userId);
        clientSideUserModel.setEmail(usersEntity.getEmail());
        return clientSideUserModel;
    }
}
