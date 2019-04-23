package com.zs.blog.service.impl;

import com.zs.blog.enums.ConfigEnum;
import com.zs.blog.exception.BusinessException;
import com.zs.blog.mapper.ConfigMapper;
import com.zs.blog.model.Config;
import com.zs.blog.model.ConfigExample;
import com.zs.blog.service.ConfigService;
import com.zs.blog.util.BeanUtil;
import com.zs.blog.vo.request.ConfigReqVo;
import com.zs.blog.vo.response.ConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @PostConstruct
    private void init() {
        for (ConfigEnum value : ConfigEnum.values()) {
            Config config = getConfigByKey(value.getKey());
            if (config == null) {
                configMapper.insertSelective(Config.builder().key(value.getKey()).name(value.getName()).build());
            }
        }
        log.info("init config");
    }

    @Override
    public void save(ConfigReqVo reqVo) {
        String key = reqVo.getKey();
        ConfigEnum configEnum = ConfigEnum.getEnumByKey(key);
        if (configEnum == null) {
            throw new BusinessException("没有此配置key");
        }

        Config config = new Config();
        BeanUtil.copy(reqVo, config);

        int i = configMapper.insertSelective(config);
        if (i <= 0) {
            configMapper.updateByPrimaryKeySelective(config);
        }
    }

    @Override
    public Config getConfigByKey(String key) {
        ConfigExample example = new ConfigExample();
        example.createCriteria().andKeyEqualTo(key);
        return configMapper.selectOneByExample(example);
    }


    @Override
    public List<ConfigVo> getConfigsByType(Integer type) {
        ConfigExample example = new ConfigExample();
        if (type != null) {
            example.createCriteria().andTypeEqualTo(type);
        }
        List<Config> configs = configMapper.selectByExample(example);
        return configs.stream()
                .map(this::genConfigVo)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConfigVo> getAllConfig() {
        return configMapper.selectAll().stream()
                .map(this::genConfigVo)
                .collect(Collectors.toList());
    }

    private ConfigVo genConfigVo(Config config) {
        ConfigVo configVo = new ConfigVo();
        BeanUtil.copy(config, configVo);
        return configVo;
    }
}
