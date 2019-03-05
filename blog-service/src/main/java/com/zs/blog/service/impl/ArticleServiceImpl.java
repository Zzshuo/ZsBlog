package com.zs.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zs.blog.exception.BusinessException;
import com.zs.blog.mapper.ArticleMapper;
import com.zs.blog.model.Article;
import com.zs.blog.model.ArticleExample;
import com.zs.blog.object.ErrorEnum;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.PageHelperUtil;
import com.zs.blog.vo.request.ArticlePageReqVo;
import com.zs.blog.vo.response.ArticleDetailVo;
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
    public ArticleDetailVo get(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new BusinessException(ErrorEnum.ERROR_NO_ARTICLE);
        }
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
        BeanUtils.copyProperties(article, articleDetailVo);
        return articleDetailVo;
    }

    @Override
    public PageInfo<ArticleVo> list(ArticlePageReqVo reqVo) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        if (Objects.nonNull(reqVo.getTypeId())) {
            criteria.andTypeIdEqualTo(reqVo.getTypeId());
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
