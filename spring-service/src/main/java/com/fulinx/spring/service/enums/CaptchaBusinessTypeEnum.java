package com.fulinx.spring.service.enums;

import com.fulinx.spring.core.utils.MessageSourceUtils;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum CaptchaBusinessTypeEnum {

    LOGIN(1, "captcha.business.type.login"),
    REGISTER(2, "captcha.business.type.register"),
    EDIT_PASSWORD(3, "captcha.business.type.edit.password"),
    RESET_PASSWORD(4, "captcha.business.type.reset.password");

    @Getter
    private final String messageKey;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, CaptchaBusinessTypeEnum> map = new LinkedHashMap<>();

    static {
        for (CaptchaBusinessTypeEnum item : CaptchaBusinessTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    CaptchaBusinessTypeEnum(Integer index, String messageKey) {
        this.messageKey = messageKey;
        this.index = index;
    }

    public String getMessage() {
        return MessageSourceUtils.getMessage(messageKey);
    }

    public static Optional<CaptchaBusinessTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getMessageByIndex(Integer index) {
        CaptchaBusinessTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.getMessage());
    }

    public static List<CaptchaBusinessTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(CaptchaBusinessTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getMessageKeyList() {
        return map.values().stream().map(CaptchaBusinessTypeEnum::getMessage).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
