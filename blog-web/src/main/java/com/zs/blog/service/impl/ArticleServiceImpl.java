package com.zs.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zs.blog.mapper.ArticleMapper;
import com.zs.blog.model.Article;
import com.zs.blog.model.ArticleExample;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.PageHelperUtil;
import com.zs.blog.vo.request.ArticleReqVo;
import com.zs.blog.vo.response.ArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ArticleVo get(Integer id) {
        return null;
    }

    @Override
    public PageInfo<ArticleVo> list(ArticleReqVo reqVo) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        if (Objects.nonNull(reqVo.getId())) {
            criteria.andIdEqualTo(reqVo.getId());
        }

        PageHelperUtil.startPage(reqVo);
        List<Article> articles = articleMapper.selectByExample(example);

        Page<ArticleVo> page = new Page<>();
        for (Article o : articles) {
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(o, articleVo);
            page.add(articleVo);
        }

        return PageHelperUtil.result(articles, page);
    }

}
