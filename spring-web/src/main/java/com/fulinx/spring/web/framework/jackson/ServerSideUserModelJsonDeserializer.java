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
import com.fulinx.spring.data.mysql.entity.*;
import com.fulinx.spring.web.framework.security.ServerSideUserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.IOException;
import java.util.*;

public class ServerSideUserModelJsonDeserializer extends JsonDeserializer<ServerSideUserModel> {
    @Override
    public ServerSideUserModel deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        Integer systemUserId = node.get("systemUserId").asInt();
        TbSystemUserEntity tbSystemUserEntity = new TbSystemUserEntity();
        TbSystemUserProfileEntity tbSystemUserProfileEntity = new TbSystemUserProfileEntity();
        TbSystemUserEntity systemUsersEntity = tbSystemUserEntity.selectById(systemUserId);
        // 取出用户资料信息
        LambdaQueryWrapper<TbSystemUserProfileEntity> tbSystemUserProfileEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbSystemUserProfileEntityLambdaQueryWrapper.eq(TbSystemUserProfileEntity::getSystemUserId, systemUserId);
        List<TbSystemUserProfileEntity> tbSystemUserProfilesEntities = tbSystemUserProfileEntity.selectList(tbSystemUserProfileEntityLambdaQueryWrapper);
        TbSystemUserProfileEntity userProfilesEntity = tbSystemUserProfilesEntities.get(0);
        ServerSideUserModel webSystemServerSideUserModel = new ServerSideUserModel();
        webSystemServerSideUserModel.setOperatorName(systemUsersEntity.getRecordUpdateName());
        webSystemServerSideUserModel.setUserProfileId(userProfilesEntity.getId());
        webSystemServerSideUserModel.setSystemUserId(systemUserId);
        webSystemServerSideUserModel.setUserType(systemUsersEntity.getUserType());
        Collection roleIds = new ArrayList<>();
        Iterator<JsonNode> roleElements = node.get("systemUserRoleIds").elements();
        while (roleElements.hasNext()) {
            JsonNode roleNext = roleElements.next();
            roleIds.add(roleNext);
        }
        webSystemServerSideUserModel.setSystemUserRoleIds(roleIds);
        webSystemServerSideUserModel.setUsername(systemUsersEntity.getUsername());
        webSystemServerSideUserModel.setGender(userProfilesEntity.getGender());
        webSystemServerSideUserModel.setTelephone(userProfilesEntity.getTelephone());
        webSystemServerSideUserModel.setOperatorName(userProfilesEntity.getTelephone());
        webSystemServerSideUserModel.setPost(userProfilesEntity.getPost());
        // 取出角色对应的权限
        LambdaQueryWrapper<TbSystemUserRoleRelationEntity> tbSystemUserRoleRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbSystemUserRoleRelationEntityLambdaQueryWrapper.eq(TbSystemUserRoleRelationEntity::getSystemUserId, systemUserId);
        // 取出角色列表
        TbSystemUserRoleRelationEntity rolesEntity = new TbSystemUserRoleRelationEntity();
        List<TbSystemUserRoleRelationEntity> tbSystemUserRoleRelationEntityList = rolesEntity.selectList(tbSystemUserRoleRelationEntityLambdaQueryWrapper);
        StringBuffer authority = new StringBuffer();
        Set<Integer> rolePermissions = new HashSet<>();
        if (tbSystemUserRoleRelationEntityList.size() > 0) {
            TbRolePermissionRelationEntity rolePermissionsEntity = new TbRolePermissionRelationEntity();
            for (TbSystemUserRoleRelationEntity tbSystemUserRoleRelationEntity : tbSystemUserRoleRelationEntityList) {
                // 取出角色对应的权限ID
                LambdaQueryWrapper<TbRolePermissionRelationEntity> tbRolePermissionRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
                tbRolePermissionRelationEntityLambdaQueryWrapper.eq(TbRolePermissionRelationEntity::getRoleId, tbSystemUserRoleRelationEntity.getRoleId());

                List<TbRolePermissionRelationEntity> tbRolePermissionRelationEntityList = rolePermissionsEntity.selectList(tbRolePermissionRelationEntityLambdaQueryWrapper);
                if (tbRolePermissionRelationEntityList.size() > 0) {
                    for (TbRolePermissionRelationEntity tbRolePermissionRelationEntity : tbRolePermissionRelationEntityList) {
                        rolePermissions.add(tbRolePermissionRelationEntity.getPermissionId());
                    }
                }
            }
        }
        if (rolePermissions.size() > 0) {
            TbPermissionEntity permissionsEntity = new TbPermissionEntity();
            for (Integer rolePermission : rolePermissions) {
                TbPermissionEntity tbPermissionEntity = permissionsEntity.selectById(rolePermission);
                authority.append(tbPermissionEntity.getPermissionCode()).append(",");
            }
        }
        if (authority != null && authority.length() > 0) {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority.toString().substring(0, authority.length() - 1));
            webSystemServerSideUserModel.setAuthorities(grantedAuthorities);
        }
        return webSystemServerSideUserModel;
    }
}
