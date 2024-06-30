package com.fulinx.spring.data.mysql.enums;

import com.fulinx.spring.core.utils.MessageSourceUtils;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum LanguageEnum {

    EN(1, "enum.language.en"),
    ZH(2, "enum.language.zh");

    @Getter
    private final String messageKey;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, LanguageEnum> map = new LinkedHashMap<>();

    static {
        for (LanguageEnum item : LanguageEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    LanguageEnum(Integer index, String messageKey) {
        this.messageKey = messageKey;
        this.index = index;
    }

    public String getMessage() {
        return MessageSourceUtils.getMessage(messageKey);
    }

    public static Optional<LanguageEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getMessageByIndex(Integer index) {
        LanguageEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.getMessage());
    }

    public static List<LanguageEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(LanguageEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getMessageKeyList() {
        return map.values().stream().map(LanguageEnum::getMessage).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
