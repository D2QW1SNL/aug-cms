/**
 * Copyright 2014-now amugua.com All right reserved. This software is the
 * confidential and proprietary information of amugua.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with amugua.com.
 */
package com.hl.aug.cms.server;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> BodyRequestFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BodyRequestFilter());
        // 过滤所有路径
        registrationBean.addUrlPatterns("/*");
        // 设置优先级
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
