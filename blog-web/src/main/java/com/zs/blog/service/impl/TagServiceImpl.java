package com.zs.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zs.blog.mapper.ArticleTagMapper;
import com.zs.blog.mapper.TagMapper;
import com.zs.blog.model.ArticleTagExample;
import com.zs.blog.model.Tag;
import com.zs.blog.model.TagExample;
import com.zs.blog.service.TagService;
import com.zs.blog.util.PageHelperUtil;
import com.zs.blog.vo.request.TagPageReqVo;
import com.zs.blog.vo.response.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageInfo<TagVo> list(TagPageReqVo reqVo) {
        TagExample example = new TagExample();

        PageHelperUtil.startPage(reqVo);
        List<Tag> tags = tagMapper.selectByExample(example);

        Page<TagVo> page = new Page<>();
        for (Tag o : tags) {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(o, tagVo);
            page.add(tagVo);
        }

        return PageHelperUtil.result(tags, page);
    }

    private TagVo genTagVo(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);

        // TODO 后面换成从redis获取
        ArticleTagExample example = new ArticleTagExample();
        ArticleTagExample.Criteria criteria = example.createCriteria();
        criteria.andTagIdEqualTo(tagVo.getId());
        int count = articleTagMapper.selectCountByExample(example);

        tagVo.setCount(count);
        return tagVo;
    }
}
