package com.zs.blog.vo.request;

import com.zs.blog.object.PageReqVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2019/3/6
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class TagReqVo extends PageReqVo {
    private Integer id;

    /**
     * 标签名
     */
    private String name;
}
