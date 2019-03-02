package com.zs.blog.vo.request;

import com.zs.blog.object.RequestVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticlePageReqVo extends RequestVo {

    private Integer typeId;

}
