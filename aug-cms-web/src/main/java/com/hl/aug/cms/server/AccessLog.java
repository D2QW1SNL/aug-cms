package com.hl.aug.cms.server;

import com.alibaba.fastjson.JSONObject;
import com.hl.aug.cms.common.enums.DateTimeFormatterEnum;
import com.hl.aug.cms.common.enums.WebHeaderEnum;
import com.hl.aug.cms.common.util.HttpIpAddrUtil;
import com.hl.aug.cms.common.util.LocalDateTimeUtil;
import com.hl.aug.cms.common.util.LoggerUtil;
import com.hl.aug.cms.dto.MimeHeadersDTO;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AccessLog {

    public static final Logger logger = LoggerUtil.INTERCEPTOR;

    public static MimeHeadersDTO printLog(HttpServletRequest request) {
        if (request instanceof BodyRequestWrapper) {
            BodyRequestWrapper requestWrapper = (BodyRequestWrapper) request;
            return AccessLog.printLog(requestWrapper, requestWrapper.getBody(), requestWrapper.getIp(), requestWrapper.getTraceId());
        } else {
            String ip = HttpIpAddrUtil.getRemoteIp(request);
            String traceId = request.getHeader(WebHeaderEnum.TRACE_ID.getCode());
            return AccessLog.printLog(request, null, ip, traceId);
        }
    }

    public static MimeHeadersDTO printLog(HttpServletRequest request, String body, String ip, String traceId) {
        JSONObject accessLog = new JSONObject();
        Map<String, String> signHeaderMap = new HashMap<>();
        // 遍历获取请求头数据
        for (WebHeaderEnum webHeader : WebHeaderEnum.values()) {
            String headerKey = webHeader.getCode();
            String headerValue = request.getHeader(headerKey);
            accessLog.put(headerKey, headerValue);
            // 存储待签名header
            if (!webHeader.isNotSign()) {
                signHeaderMap.put(headerKey, headerValue);
            }
        }
        MimeHeadersDTO headersDTO = accessLog.toJavaObject(MimeHeadersDTO.class);
        // 获取待验签header
        headersDTO.setSignHeaderMap(signHeaderMap);
        // 为了accessLog的日志跟踪
        accessLog.put(WebHeaderEnum.TRACE_ID.getCode(), traceId);
        accessLog.put("requestData", body);
        accessLog.put("url", request.getRequestURI());
        accessLog.put("method", request.getMethod());
        accessLog.put("queryString", request.getQueryString());
        accessLog.put("requestClientIp", ip);
        accessLog.put("serverTime", LocalDateTimeUtil.format(LocalDateTime.now(), DateTimeFormatterEnum.FORMAT_YMD_HMSsss));
        logger.info(accessLog.toJSONString());
        return headersDTO;
    }
}
