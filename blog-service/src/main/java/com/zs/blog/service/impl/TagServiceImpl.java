package com.zs.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zs.blog.enums.ResponseEnum;
import com.zs.blog.exception.BusinessException;
import com.zs.blog.mapper.ArticleTagMapper;
import com.zs.blog.mapper.TagMapper;
import com.zs.blog.model.ArticleTagExample;
import com.zs.blog.model.Tag;
import com.zs.blog.model.TagExample;
import com.zs.blog.service.TagService;
import com.zs.blog.util.BeanUtil;
import com.zs.blog.util.PageHelperUtil;
import com.zs.blog.vo.request.TagPageReqVo;
import com.zs.blog.vo.request.TagReqVo;
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

    @Override
    public void addOrUpdate(TagReqVo reqVo) {
        Tag tag = new Tag();
        BeanUtil.copy(reqVo, tag);
        if (tag.getId() != null) {
            tagMapper.updateByPrimaryKeySelective(tag);
        } else {
            tagMapper.insertSelective(tag);
        }
    }

    @Override
    public void delete(Integer id) {
        int i = tagMapper.deleteByPrimaryKey(id);
        if (i <= 0) {
            throw new BusinessException(ResponseEnum.ERROR_NO_TAG);
        }
    }

    @Override
    public boolean exist(Integer id) {
        return tagMapper.existsWithPrimaryKey(id);
    }

    @Override
    public TagVo get(Integer id) {
        Tag tag = tagMapper.selectByPrimaryKey(id);
        if (tag == null) {
            throw new BusinessException(ResponseEnum.ERROR_NO_TAG);
        }
        TagVo tagVo = new TagVo();
        BeanUtil.copy(tag, tagVo);
        return tagVo;
    }

    @Override
    public PageInfo<TagVo> list(TagPageReqVo reqVo) {
        TagExample example = genExample(reqVo);

        PageHelperUtil.startPage(reqVo);
        List<Tag> tags = tagMapper.selectByExample(example);

        Page<TagVo> page = new Page<>();
        for (Tag o : tags) {
            page.add(genTagVo(o));
        }

        return PageHelperUtil.result(tags, page);
    }

    @Override
    public List<TagVo> getAll() {
        List<Tag> tags = tagMapper.selectAll();
        return tags.stream()
                .map(this::genTagVo)
                .collect(Collectors.toList());
    }

    private TagExample genExample(TagPageReqVo reqVo) {
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

    private TagVo genTagVo(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtil.copy(tag, tagVo);

        // TODO 后面换成从redis获取
        ArticleTagExample example = new ArticleTagExample();
        ArticleTagExample.Criteria criteria = example.createCriteria();
        criteria.andTagIdEqualTo(tagVo.getId());
        int count = articleTagMapper.selectCountByExample(example);

        tagVo.setCount(count);
        return tagVo;
    }
}
