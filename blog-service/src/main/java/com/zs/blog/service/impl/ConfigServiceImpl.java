package com.zs.blog.service.impl;

import com.zs.blog.enums.ConfigEnum;
import com.zs.blog.enums.ConfigTypeEnum;
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

/**
 * @author zshuo
 */
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
                configMapper.insertSelective(Config.builder().configKey(value.getKey()).configName(value.getName()).build());
            }
        }
        log.info("init config");
    }

    @Override
    public void save(ConfigReqVo reqVo) {
        ConfigEnum configEnum = ConfigEnum.getEnumByKey(reqVo.getConfigKey());
        if (configEnum == null) {
            throw new BusinessException("没有此configKey");
        }

        ConfigTypeEnum configTypeEnum = ConfigTypeEnum.getEnumById(reqVo.getId());
        if (configTypeEnum == null) {
            throw new BusinessException("没有此ConfigType");
        }

        Config config = new Config();
        BeanUtil.copy(reqVo, config);

        configMapper.updateByPrimaryKeySelective(config);
    }

    @Override
    public Config getConfigByKey(String key) {
        ConfigExample example = new ConfigExample();
        example.createCriteria().andConfigKeyEqualTo(key);
        return configMapper.selectOneByExample(example);
    }

    @Override
    public List<ConfigVo> getConfigsByType(Integer type) {
        ConfigExample example = new ConfigExample();
        if (type != null) {
            example.createCriteria().andConfigTypeEqualTo(type);
        }
        List<Config> configs = configMapper.selectByExample(example);
        return configs.stream()
                .map(this::genConfigVo)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConfigVo> getAllConfig() {
        return getConfigsByType(null);
    }

    private ConfigVo genConfigVo(Config config) {
        ConfigVo configVo = new ConfigVo();
        BeanUtil.copy(config, configVo);
        return configVo;
    }
}
