package com.zs.blog.vo.request;

import com.zs.blog.object.PageReqVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2019-05-08
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class TypeReqVo extends PageReqVo {

    private Integer id;

    /**
     * 分类
     */
    private String name;
}
