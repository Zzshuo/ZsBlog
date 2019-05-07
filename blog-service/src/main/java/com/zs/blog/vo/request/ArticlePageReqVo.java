package com.zs.blog.vo.request;

import com.zs.blog.object.PageReqVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticlePageReqVo extends PageReqVo {

    private Integer typeId;

}
