package com.hl.aug.cms.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BodyRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String contentType = request.getContentType();
        if (StringUtils.isNotBlank(contentType) && contentType.contains("application/x-www-form-urlencoded")) {
            request.getParameterMap();
        }
        BodyRequestWrapper requestWrapper = new BodyRequestWrapper(request);
        filterChain.doFilter(requestWrapper, response);
    }
}
