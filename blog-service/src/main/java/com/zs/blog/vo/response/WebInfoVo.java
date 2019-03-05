package com.zs.blog.vo.response;

import lombok.Data;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@Data
public class WebInfoVo {
    /**
     * 文章总数
     */
    private Integer articleCount;
    /**
     * 标签总数
     */
    private Integer tagCount;
    /**
     * 留言总数
     */
    private Integer msgCount;
    /**
     * 在线人数
     */
    private Integer online;
    /**
     * 运行天数
     */
    private Integer runDay;
}
