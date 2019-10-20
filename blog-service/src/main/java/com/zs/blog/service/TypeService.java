package com.zs.blog.service;

import com.zs.blog.object.Page;
import com.zs.blog.vo.request.TypeReqVo;
import com.zs.blog.vo.response.TypeVo;

import java.util.List;

/**
 * @author zshuo
 * @date 2019-05-08
 **/
public interface TypeService {

    /**
     * 添加/修改分类
     *
     * @param reqVo
     */
    void save(TypeReqVo reqVo);

    /**
     * 删除分类
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取分类
     *
     * @param id
     * @return
     */
    TypeVo get(Integer id);

    /**
     * 分类列表
     *
     * @param reqVo
     * @return
     */
    Page<TypeVo> list(TypeReqVo reqVo);

    /**
     * 获取所有分类
     *
     * @return
     */
    List<TypeVo> getAll();
}
