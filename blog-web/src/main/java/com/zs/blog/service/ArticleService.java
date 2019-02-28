package com.zs.blog.service;

import com.github.pagehelper.PageInfo;
import com.zs.blog.vo.request.ArticlePageReqVo;
import com.zs.blog.vo.response.ArticleDetailVo;
import com.zs.blog.vo.response.ArticleVo;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
public interface ArticleService {

    /**
     * 获取文章
     *
     * @param id
     * @return
     */
    ArticleDetailVo get(Integer id);

    /**
     * 文章列表
     *
     * @param reqVo
     * @return
     */
    PageInfo<ArticleVo> list(ArticlePageReqVo reqVo);
}
