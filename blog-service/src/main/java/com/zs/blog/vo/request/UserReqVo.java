package com.zs.blog.vo.request;

import com.zs.blog.object.PageReqVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserReqVo extends PageReqVo {
    private String username;

    private String password;
}
