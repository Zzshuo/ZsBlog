package com.zs.blog.vo.request;

import com.zs.blog.object.RequestVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleReqVo extends RequestVo {

    private Integer id;

}
