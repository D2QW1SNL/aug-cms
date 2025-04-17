package com.hl.aug.cms.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2025/4/17 10:40
 * @Version: 1.0.0
 */
@Component
public class AugCmsPathHandler {

    @Autowired
    private WebApplicationContext webApplicationContext;

//    @Autowired
//    private ICacheService cacheService;

    /**
     * 所有接口路径 admin_interface_path:{appId}
     */
    public static final String INTERFACE_PATH = "admin_interface_path:%s";

    public List<String> getAllAddTagAnnotationUrl() {

        RequestMappingHandlerMapping mappingHandlerMapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);

        Map<RequestMappingInfo, HandlerMethod> map = mappingHandlerMapping.getHandlerMethods();
        List<String> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {

            RequestMappingInfo info = m.getKey();

            HandlerMethod method = m.getValue();

            PatternsRequestCondition p = info.getPatternsCondition();
            if (method.getMethod().getDeclaringClass().getName().startsWith("com.hl.aug.cms.controller")) {

                list.addAll(p.getPatterns());
            }

        }
        return list;
    }


    @PostConstruct
    public void init() {
        List<String> list = getAllAddTagAnnotationUrl();
//        cacheService.delete(String.format(INTERFACE_PATH, AppIdEnum.KAI_LIAN.getId()));
//        cacheService.sAdd(String.format(INTERFACE_PATH, AppIdEnum.KAI_LIAN.getId()), list.toArray(new String[0]));
    }



}
