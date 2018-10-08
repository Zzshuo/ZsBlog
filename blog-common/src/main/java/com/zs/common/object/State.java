package com.zs.common.object;

import java.util.Objects;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
public enum State {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功！"),
    ERROR(500, "服务器未知错误！")
    ;

    private Integer code;
    private String message;

    State(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static State getResponseStatus(String message) {
        for (State s : State.values()) {
            if (Objects.equals(s.getMessage(), message)) {
                return s;
            }
        }
        return null;
    }

}
