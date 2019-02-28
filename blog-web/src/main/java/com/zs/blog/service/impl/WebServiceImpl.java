package com.zs.blog.service.impl;

import com.zs.blog.mapper.ArticleMapper;
import com.zs.blog.mapper.TagMapper;
import com.zs.blog.service.WebService;
import com.zs.blog.vo.response.WebInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public WebInfoVo info() {
        // TODO 后面换成从redis获取
        WebInfoVo webInfoVo = new WebInfoVo();
        webInfoVo.setArticleCount(articleMapper.selectCountByExample(null));
        webInfoVo.setTagCount(tagMapper.selectCountByExample(null));
        webInfoVo.setMsgCount(0);
        webInfoVo.setOnline(0);
        webInfoVo.setRunDay(0);
        return webInfoVo;
    }
}
