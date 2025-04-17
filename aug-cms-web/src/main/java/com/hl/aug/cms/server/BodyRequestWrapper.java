package com.hl.aug.cms.server;

import com.hl.aug.cms.common.enums.WebHeaderEnum;
import com.hl.aug.cms.common.util.HttpIpAddrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


public class BodyRequestWrapper extends HttpServletRequestWrapper {

    private String body;

    private String traceId;

    private String ip;

    public BodyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream inputStream = request.getInputStream();
        String traceId = request.getHeader(WebHeaderEnum.TRACE_ID.getCode());
        this.traceId = StringUtils.isNotBlank(traceId) ? traceId : "uuid-" + UUID.randomUUID();
        this.ip = HttpIpAddrUtil.getRemoteIp(request);
        this.body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getIp() {
        return ip;
    }
}