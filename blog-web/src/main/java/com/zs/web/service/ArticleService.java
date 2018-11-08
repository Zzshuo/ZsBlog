package com.zs.web.service;

import com.github.pagehelper.PageInfo;
import com.zs.web.vo.request.ArticleReqVo;
import com.zs.web.vo.response.ArticleVo;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
public interface ArticleService {

    /**
     * 文章列表
     *
     * @param reqVo
     * @return
     */
    PageInfo<ArticleVo> list(ArticleReqVo reqVo);
}
