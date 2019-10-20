package com.zs.blog.service;

import com.zs.blog.object.Page;
import com.zs.blog.vo.request.ArticleByTagReqVo;
import com.zs.blog.vo.request.ArticleReqVo;
import com.zs.blog.vo.response.ArticleBriefVo;
import com.zs.blog.vo.response.ArticleVo;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
public interface ArticleService {

    /**
     * 发布/修改文章
     *
     * @param reqVo
     */
    void save(ArticleReqVo reqVo);

    /**
     * 删除文章
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取文章
     *
     * @param id
     * @return
     */
    ArticleVo get(Integer id);

    /**
     * 文章列表
     *
     * @param reqVo
     * @return
     */
    Page<ArticleBriefVo> list(ArticleReqVo reqVo);

    Page<ArticleBriefVo> getListByTagId(ArticleByTagReqVo reqVo);
}
