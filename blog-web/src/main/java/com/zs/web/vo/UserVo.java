package com.zs.web.vo;

import com.zs.common.object.RequestVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVo extends RequestVo {
    private String username;

    private String password;
}
