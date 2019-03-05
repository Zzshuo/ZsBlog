package com.zs.blog.service;

import com.github.pagehelper.PageInfo;
import com.zs.blog.vo.request.TagPageReqVo;
import com.zs.blog.vo.response.TagVo;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
public interface TagService {


    /**
     * 标签列表
     *
     * @return
     */
    PageInfo<TagVo> list(TagPageReqVo reqVo);
}
