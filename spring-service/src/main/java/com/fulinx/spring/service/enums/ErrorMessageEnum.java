/*
 * Copyright (c) Xipu Tech. 2023.
 */

package com.fulinx.spring.service.enums;

import com.fulinx.spring.core.utils.MessageSourceUtils;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum ErrorMessageEnum {

    /**
     * User
     */
    USER_NOT_EXIST(-100001, "error.user.not.exists"),
    USERNAME_ALREADY_EXISTS(-100002, "error.username.exists"),
    USER_ADD_FAIL(-100003, "error.user.add.fail"),
    USER_ARCHIVE_ADD_FAIL(-100004, "error.user.archive.add.fail"),
    USERNAME_PASSWORD_INCORRECT(-100005, "error.username.password.incorrect"),
    USER_DISABLED(-100006, "error.user.disabled"),
    USER_ROLE_ADD_FAIL(-100007, "error.user.role.add.fail"),
    USER_OLD_PASSWORD_INCORRECT(-100008, "error.user.old.password.incorrect"),
    USER_NOT_ADMIN(-100009, "error.user.not.admin"),
    USER_UPDATE_FAIL(-100010, "error.user.update.fail"),
    NOT_ALLOW_ADD_SUPERADMIN(-100011, "error.not.allow.add.superadmin"),
    NOT_ALLOW_UPDATE_SUPERADMIN(-100012, "error.not.allow.update.superadmin"),
    NOT_ALLOW_DELETE_SUPERADMIN(-100013, "error.not.allow.delete.superadmin"),
    NOT_ALLOW_DELETE_SELF(-100014, "error.not.allow.delete.self"),
    VERIFICATION_CODE_INCORRECT(-100015, "error.verification.code.incorrect"),

    /**
     * User Profile
     */
    USER_ACCOUNT_DATA_CREATE_FAIL(-100101, "error.user.account.data.create.fail"),
    USER_ARCHIVE_DATA_NOT_EXISTS(-100102, "error.user.archive.data.not.exists"),
    USER_ARCHIVE_DATA_CREATE_FAIL(-100103, "error.user.archive.data.create.fail"),

    /**
     * Role
     */
    ROLE_CREATE_FAIL(-101002, "error.role.create.fail"),
    ROLE_NAME_DUPLICATE(-101003, "error.role.name.duplicate"),
    ROLE_PERMISSION_CANNOT_BE_EMPTY(-101004, "error.role.permission.cannot.be.empty"),
    CANNOT_CREATE_SUPERADMIN(-101005, "error.cannot.create.superadmin"),
    ROLE_NOT_EXISTS(-101006, "error.role.not.exists"),
    ROLE_UPDATE_FAIL(-101007, "error.role.update.fail"),
    ROLE_REFERENCED(-101008, "error.role.referenced"),

    /**
     * Permissions
     */
    PERMISSION_CREATE_FAIL(-102001, "error.permission.create.fail"),
    PERMISSION_IDENTIFIER_DUPLICATE(-102002, "error.permission.identifier.duplicate"),
    PERMISSION_DATA_NOT_EXISTS(-102003, "error.permission.data.not.exists"),
    CAPTCHA_BUSINESS_TYPE_INCORRECT(-102004, "error.captcha.business.type.incorrect"),

    /**
     * Other
     */
    DATA_VERSION_CHANGED_RETRY(-900901, "error.data.version.changed.retry"),
    UPDATE_EXCEPTION_DATA_VERSION_CHANGED(-900909, "error.update.exception.data.version.changed"),
    SYSTEM_ERROR(-900999, "error.system");

    @Getter
    private final String messageKey;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, ErrorMessageEnum> map = new LinkedHashMap<>();

    static {
        for (ErrorMessageEnum item : ErrorMessageEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    ErrorMessageEnum(Integer index, String messageKey) {
        this.messageKey = messageKey;
        this.index = index;
    }

    public String getMessage() {
        return MessageSourceUtils.getMessage(messageKey);
    }

    public static Optional<ErrorMessageEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getMessageByIndex(Integer index) {
        ErrorMessageEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.getMessage());
    }

    public static List<ErrorMessageEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(ErrorMessageEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getMessageKeyList() {
        return map.values().stream().map(ErrorMessageEnum::getMessage).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
