package com.zs.blog.service;

import com.zs.blog.object.Page;
import com.zs.blog.vo.request.TagReqVo;
import com.zs.blog.vo.response.TagDetailVo;
import com.zs.blog.vo.response.TagVo;

import java.util.List;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
public interface TagService {

    /**
     * 添加/修改标签
     *
     * @param reqVo
     */
    void save(TagReqVo reqVo);

    /**
     * 删除标签
     *
     * @param reqVo
     */
    void delete(TagReqVo reqVo);

    /**
     * 获取标签
     *
     * @param reqVo
     * @return
     */
    TagDetailVo get(TagReqVo reqVo);

    /**
     * 标签列表
     *
     * @param reqVo
     * @return
     */
    Page<TagDetailVo> list(TagReqVo reqVo);

    /**
     * 获取所有标签
     *
     * @return
     */
    List<TagDetailVo> getAll();

    /**
     * 获取文章标签
     *
     * @return
     */
    List<TagVo> getTagsByArticleId(Integer articleId);
}
