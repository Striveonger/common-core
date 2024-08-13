package com.striveonger.common.core.utils;

import java.util.regex.Pattern;

/**
 * @author Mr.Lee
 * @description: 基于规则的校验
 * @date 2023-02-28 18:26
 */
public enum RegularVerify {

    IPV4("^(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"),

    ;

    private final Pattern pattern;

    RegularVerify(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public boolean verify(Object o) {
        String str;
        if (o instanceof String s) {
            str = s;
        } else {
            if (o == null) {
                return false;
            }
            str = String.valueOf(o);
        }
        return this.pattern.matcher(str).matches();
    }

}
