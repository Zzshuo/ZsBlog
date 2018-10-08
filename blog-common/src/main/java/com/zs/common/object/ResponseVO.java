package com.zs.common.object;

import com.github.pagehelper.PageInfo;
import lombok.Data;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
@Data
public class ResponseVO<T> {
    private Integer code;
    private String message;
    private T data;

    /**
     * 分页信息
     */
    private PageInfo pageInfo;

    public ResponseVO(State state) {
        this.code = state.getCode();
        this.message = state.getMessage();
    }

    public ResponseVO(State state, T data) {
        this.code = state.getCode();
        this.message = state.getMessage();
        this.data = data;
    }

    public ResponseVO(State state, T data, PageInfo pageInfo) {
        this.code = state.getCode();
        this.message = state.getMessage();
        this.data = data;
        this.pageInfo = pageInfo;
    }

    public ResponseVO(Integer code, String message, T data, PageInfo pageInfo) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.pageInfo = pageInfo;
    }
}
