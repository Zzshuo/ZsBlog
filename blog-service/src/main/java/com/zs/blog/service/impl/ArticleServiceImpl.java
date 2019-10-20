package com.zs.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.blog.enums.ResponseEnum;
import com.zs.blog.exception.BusinessException;
import com.zs.blog.mapper.ArticleMapper;
import com.zs.blog.mapper.ArticleTagMapper;
import com.zs.blog.mapper.SelfMapper;
import com.zs.blog.model.Article;
import com.zs.blog.model.ArticleExample;
import com.zs.blog.model.ArticleTag;
import com.zs.blog.model.ArticleTagExample;
import com.zs.blog.object.Page;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.BeanUtil;
import com.zs.blog.util.PageUtils;
import com.zs.blog.vo.request.ArticleByTagReqVo;
import com.zs.blog.vo.request.ArticleReqVo;
import com.zs.blog.vo.response.ArticleBriefVo;
import com.zs.blog.vo.response.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SelfMapper selfMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ArticleReqVo reqVo) {
        Article article = new Article();
        BeanUtil.copy(reqVo, article);
        if (article.getId() != null) {
            articleMapper.updateByPrimaryKeySelective(article);
        } else {
            articleMapper.insertSelective(article);
        }

        List<Integer> tagIdList = reqVo.getTagIdList();
        for (Integer tagId : tagIdList) {
            ArticleTagExample example = new ArticleTagExample();
            example.createCriteria()
                    .andArticleIdEqualTo(article.getId())
                    .andTagIdEqualTo(tagId);
            int count = articleTagMapper.selectCountByExample(example);

            if (count == 0) {
                ArticleTag articleTag = ArticleTag
                        .builder()
                        .tagId(tagId)
                        .articleId(article.getId())
                        .build();
                articleTagMapper.insertSelective(articleTag);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        int i = articleMapper.deleteByPrimaryKey(id);
        if (i <= 0) {
            throw new BusinessException(ResponseEnum.ARTICLE_NOT_EXIST);
        }
        ArticleTagExample example = new ArticleTagExample();
        example.createCriteria()
                .andArticleIdEqualTo(id);
        articleTagMapper.deleteByExample(example);
    }

    @Override
    public ArticleVo get(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new BusinessException(ResponseEnum.ARTICLE_NOT_EXIST);
        }
        ArticleVo articleVo = new ArticleVo();
        BeanUtil.copy(article, articleVo);

        List<Integer> tagIdList = selfMapper.getTagIdListByArticleId(id);
        articleVo.setTagIdList(tagIdList);
        articleVo.setViews(0);
        articleVo.setComments(0);
        return articleVo;
    }

    @Override
    public Page<ArticleBriefVo> list(ArticleReqVo reqVo) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        if (Objects.nonNull(reqVo.getTypeId())) {
            criteria.andTypeIdEqualTo(reqVo.getTypeId());
        }

        PageHelper.startPage(reqVo.getPageNum(), reqVo.getPageSize());
        List<Article> articles = articleMapper.selectByExample(example);

        return PageUtils.toPage(articles, this::getArticleBriefVo);
    }

    @Override
    public Page<ArticleBriefVo> getListByTagId(ArticleByTagReqVo reqVo) {
        if (Objects.isNull(reqVo.getTagId())) {
            return null;
        }

        PageHelper.startPage(reqVo.getPageNum(), reqVo.getPageSize());
        List<Article> articles = selfMapper.getArticleByTagId(reqVo.getTagId());

        return PageUtils.toPage(articles, this::getArticleBriefVo);
    }

    private ArticleBriefVo getArticleBriefVo(Article article) {
        ArticleBriefVo articleBriefVo = BeanUtil.copy(article, ArticleBriefVo.class);

        // tagIdList
        List<Integer> tagIdList = selfMapper.getTagIdListByArticleId(article.getId());
        articleBriefVo.setTagIdList(tagIdList);
        // 浏览次数 评论次数
        articleBriefVo.setViews(0);
        articleBriefVo.setComments(0);
        return articleBriefVo;
    }
}
