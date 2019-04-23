package com.zs.blog.service;

import com.zs.blog.model.Config;
import com.zs.blog.vo.request.ConfigReqVo;
import com.zs.blog.vo.response.ConfigVo;

import java.util.List;

public interface ConfigService {


    void save(ConfigReqVo reqVo);

    Config getConfigByKey(String key);

    List<ConfigVo> getConfigsByType(Integer type);

    List<ConfigVo> getAllConfig();
}
