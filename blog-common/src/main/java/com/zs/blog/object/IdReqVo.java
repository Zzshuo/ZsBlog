package com.zs.blog.object;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2019-05-07
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class IdReqVo extends RequestVo {
    private Integer id;
}
