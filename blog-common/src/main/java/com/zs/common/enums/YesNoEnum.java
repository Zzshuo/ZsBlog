package com.zs.common.enums;

import java.util.Objects;

/**
 * 是否枚举
 *
 * @author zshuo
 * @date 2018/11/6
 **/
public enum YesNoEnum {

    /**
     * 是
     */
    YES(1, true, "YES", "是"),
    /**
     * 否
     */
    NO(0, false, "NO", "否");

    private Integer value;
    private Boolean bool;
    private String ename;
    private String cname;

    YesNoEnum(Integer value, Boolean bool, String ename, String cname) {
        this.value = value;
        this.bool = bool;
        this.ename = ename;
        this.cname = cname;
    }

    public Integer getValue() {
        return value;
    }

    public Boolean getBool() {
        return bool;
    }

    public String getEname() {
        return ename;
    }

    public String getCname() {
        return cname;
    }

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
