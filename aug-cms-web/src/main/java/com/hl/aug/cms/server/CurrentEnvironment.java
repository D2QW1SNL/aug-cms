package com.hl.aug.cms.server;

import com.hl.aug.cms.common.constant.ConfigProperties;
import com.hl.aug.cms.common.enums.CommonErrorEnum;
import com.hl.aug.cms.common.util.Assert;
import com.hl.aug.cms.common.util.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class CurrentEnvironment implements EnvironmentAware {

    private Logger logger = LoggerUtil.COMMON_DEFAULT;

    private Environment environment;

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        // logger.info("CurrentEnvironment: {}", environment);
        Assert.notNull(environment.getProperty("deploy.mode"), CommonErrorEnum.PARAM_CHECK_ERROR, "部署环境为空", true);
        ConfigProperties.setDeployMode(environment.getProperty("deploy.mode"));
//        ConfigProperties.setServerVersion(environment.getProperty("serverVersion"));
//        ConfigProperties.setServerName(environment.getProperty("serverName"));
    }
}