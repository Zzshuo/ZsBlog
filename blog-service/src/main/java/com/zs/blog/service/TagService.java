package com.zs.blog.service;

import com.github.pagehelper.PageInfo;
import com.zs.blog.vo.request.TagPageReqVo;
import com.zs.blog.vo.request.TagReqVo;
import com.zs.blog.vo.response.TagVo;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
public interface TagService {

    /**
     * 发布/修改标签
     *
     * @param reqVo
     */
    void addOrUpdate(TagReqVo reqVo);

    /**
     * 删除标签
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 标签是否存在
     *
     * @param id
     * @return
     */
    boolean exist(Integer id);

    /**
     * 获取标签
     *
     * @param id
     * @return
     */
    TagVo get(Integer id);

    /**
     * 标签列表
     *
     * @param reqVo
     * @return
     */
    PageInfo<TagVo> list(TagPageReqVo reqVo);
}