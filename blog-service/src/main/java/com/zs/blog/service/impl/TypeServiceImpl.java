package com.zs.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.zs.blog.enums.ResponseEnum;
import com.zs.blog.exception.BusinessException;
import com.zs.blog.mapper.ArticleMapper;
import com.zs.blog.mapper.TypeMapper;
import com.zs.blog.model.ArticleExample;
import com.zs.blog.model.Type;
import com.zs.blog.model.TypeExample;
import com.zs.blog.object.Page;
import com.zs.blog.service.TypeService;
import com.zs.blog.util.BeanUtil;
import com.zs.blog.util.PageUtils;
import com.zs.blog.vo.request.TypeReqVo;
import com.zs.blog.vo.response.TypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zshuo
 * @date 2019-05-08
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void save(TypeReqVo reqVo) {
        checkTypeExist(reqVo.getName());

        Type type = new Type();
        BeanUtil.copy(reqVo, type);
        if (type.getId() != null) {
            typeMapper.updateByPrimaryKeySelective(type);
        } else {
            typeMapper.insertSelective(type);
        }
    }

    private void checkTypeExist(String name) {
        TypeExample example = new TypeExample();
        example.createCriteria().andNameEqualTo(name);
        Type exist = typeMapper.selectOneByExample(example);
        if (exist != null) {
            throw new BusinessException(ResponseEnum.TYPE_EXIST, name);
        }
    }

    @Override
    public void delete(Integer id) {
        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andTypeIdEqualTo(id);
        int count = articleMapper.selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException(ResponseEnum.TYPE_IN_USE);
        }

        int i = typeMapper.deleteByPrimaryKey(id);
        if (i <= 0) {
            throw new BusinessException(ResponseEnum.TYPE_NOT_EXIST);
        }
    }


    @Override
    public TypeVo get(Integer id) {
        Type type = typeMapper.selectByPrimaryKey(id);
        if (type == null) {
            throw new BusinessException(ResponseEnum.TYPE_NOT_EXIST);
        }
        TypeVo typeVo = new TypeVo();
        BeanUtil.copy(type, typeVo);
        return typeVo;
    }

    @Override
    public Page<TypeVo> list(TypeReqVo reqVo) {
        TypeExample example = genExample(reqVo);

        PageHelper.startPage(reqVo.getPageNum(), reqVo.getPageSize());
        List<Type> types = typeMapper.selectByExample(example);

        List<TypeVo> typeVos = types.stream()
                .map(this::genTypeVo)
                .collect(Collectors.toList());

        return PageUtils.toPage(types, this::genTypeVo);
    }

    @Override
    public List<TypeVo> getAll() {
        List<Type> types = typeMapper.selectAll();
        return types.stream()
                .map(this::genTypeVo)
                .collect(Collectors.toList());
    }

    private TypeExample genExample(TypeReqVo reqVo) {
        TypeExample example = new TypeExample();
        TypeExample.Criteria criteria = example.createCriteria();
        if (reqVo.getId() != null) {
            criteria.andIdEqualTo(reqVo.getId());
        }
        if (reqVo.getName() != null) {
            criteria.andNameEqualTo(reqVo.getName());
        }
        return example;
    }

    private TypeVo genTypeVo(Type type) {
        TypeVo typeVo = new TypeVo();
        BeanUtil.copy(type, typeVo);

        // TODO 后面换成从redis获取
        ArticleExample example = new ArticleExample();
        example.createCriteria().andTypeIdEqualTo(typeVo.getId());
        int count = articleMapper.selectCountByExample(example);

        typeVo.setCount(count);
        return typeVo;
    }
}
