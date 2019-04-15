package com.zs.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 是否枚举
 *
 * @author zshuo
 * @date 2018/11/6
 **/
@AllArgsConstructor
@Getter
public enum YesNoEnum {

    YES(1, true, "YES", "是"),
    NO(0, false, "NO", "否"),
    ;

    private Integer value;
    private Boolean bool;
    private String ename;
    private String cname;

    public YesNoEnum getEnumByValue(Integer value) {
        if (Objects.isNull(value)) {
            return null;
        }
        for (YesNoEnum yesNoEnum : YesNoEnum.values()) {
            if (Objects.equals(yesNoEnum.getValue(), value)) {
                return yesNoEnum;
            }
        }
        return null;
    }

    public YesNoEnum getEnumByBool(Boolean bool) {
        if (Objects.isNull(bool)) {
            return null;
        }
        for (YesNoEnum yesNoEnum : YesNoEnum.values()) {
            if (Objects.equals(yesNoEnum.getBool(), bool)) {
                return yesNoEnum;
            }
        }
        return null;
    }

    public YesNoEnum getEnumByEname(String ename) {
        if (Objects.isNull(ename)) {
            return null;
        }
        for (YesNoEnum yesNoEnum : YesNoEnum.values()) {
            if (Objects.equals(yesNoEnum.getEname(), ename)) {
                return yesNoEnum;
            }
        }
        return null;
    }

    public YesNoEnum getEnumByCname(String cname) {
        if (Objects.isNull(cname)) {
            return null;
        }
        for (YesNoEnum yesNoEnum : YesNoEnum.values()) {
            if (Objects.equals(yesNoEnum.getCname(), cname)) {
                return yesNoEnum;
            }
        }
        return null;
    }
}
