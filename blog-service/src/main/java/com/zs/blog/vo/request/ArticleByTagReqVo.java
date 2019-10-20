package com.zs.blog.vo.request;

import com.zs.blog.object.PageReqVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2019-09-30
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleByTagReqVo extends PageReqVo {

    private Integer tagId;
}
