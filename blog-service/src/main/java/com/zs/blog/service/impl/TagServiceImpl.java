package com.zs.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.blog.enums.ResponseEnum;
import com.zs.blog.exception.BusinessException;
import com.zs.blog.mapper.ArticleTagMapper;
import com.zs.blog.mapper.SelfMapper;
import com.zs.blog.mapper.TagMapper;
import com.zs.blog.model.ArticleTagExample;
import com.zs.blog.model.Tag;
import com.zs.blog.model.TagExample;
import com.zs.blog.object.Page;
import com.zs.blog.service.TagService;
import com.zs.blog.util.BeanUtil;
import com.zs.blog.util.PageUtils;
import com.zs.blog.vo.request.TagReqVo;
import com.zs.blog.vo.response.TagDetailVo;
import com.zs.blog.vo.response.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private SelfMapper selfMapper;

    @Override
    public void save(TagReqVo reqVo) {
        checkTagExist(reqVo.getName());

        Tag tag = new Tag();
        BeanUtil.copy(reqVo, tag);
        if (tag.getId() != null) {
            tagMapper.updateByPrimaryKeySelective(tag);
        } else {
            tagMapper.insertSelective(tag);
        }
    }

    private void checkTagExist(String tagName) {
        TagExample example = new TagExample();
        example.createCriteria().andNameEqualTo(tagName);
        Tag exist = tagMapper.selectOneByExample(example);
        if (exist != null) {
            throw new BusinessException(ResponseEnum.TAG_EXIST, tagName);
        }
    }

    @Override
    public void delete(TagReqVo reqVo) {
        ArticleTagExample example = new ArticleTagExample();
        example.createCriteria()
                .andTagIdEqualTo(reqVo.getId());
        int count = articleTagMapper.selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException(ResponseEnum.TAG_IN_USE);
        }

        int i = tagMapper.deleteByPrimaryKey(reqVo.getId());
        if (i <= 0) {
            throw new BusinessException(ResponseEnum.TAG_NOT_EXIST);
        }
    }


    @Override
    public TagDetailVo get(TagReqVo reqVo) {
        Tag tag = tagMapper.selectByPrimaryKey(reqVo.getId());
        if (tag == null) {
            throw new BusinessException(ResponseEnum.TAG_NOT_EXIST);
        }
        TagDetailVo tagDetailVo = new TagDetailVo();
        BeanUtil.copy(tag, tagDetailVo);
        return tagDetailVo;
    }

    @Override
    public Page<TagDetailVo> list(TagReqVo reqVo) {
        TagExample example = genExample(reqVo);

        PageHelper.startPage(reqVo.getPageNum(), reqVo.getPageSize());
        List<Tag> tags = tagMapper.selectByExample(example);

        return PageUtils.toPage(tags, this::genTagDetailVo);
    }

    @Override
    public List<TagDetailVo> getAll() {
        List<Tag> tags = tagMapper.selectAll();
        return tags.stream()
                .map(this::genTagDetailVo)
                .collect(Collectors.toList());
    }

    private TagExample genExample(TagReqVo reqVo) {
        TagExample example = new TagExample();
        TagExample.Criteria criteria = example.createCriteria();
        if (reqVo.getId() != null) {
            criteria.andIdEqualTo(reqVo.getId());
        }
        if (reqVo.getName() != null) {
            criteria.andNameEqualTo(reqVo.getName());
        }
        return example;
    }

    private TagDetailVo genTagDetailVo(Tag tag) {
        TagDetailVo tagDetailVo = BeanUtil.copy(tag, TagDetailVo.class);

        // TODO 后面换成从redis获取
        ArticleTagExample example = new ArticleTagExample();
        ArticleTagExample.Criteria criteria = example.createCriteria();
        criteria.andTagIdEqualTo(tagDetailVo.getId());
        int count = articleTagMapper.selectCountByExample(example);

        tagDetailVo.setCount(count);
        return tagDetailVo;
    }


    @Override
    public List<TagVo> getTagsByArticleId(Integer articleId) {
        List<Tag> taglist = selfMapper.getTagListByArticleId(articleId);
        return taglist.stream().map(this::genTagVo).collect(Collectors.toList());
    }

    private TagVo genTagVo(Tag tag) {
        return BeanUtil.copy(tag, TagVo.class);
    }
}
