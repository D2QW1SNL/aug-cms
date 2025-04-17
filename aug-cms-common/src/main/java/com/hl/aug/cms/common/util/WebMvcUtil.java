package com.hl.aug.cms.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * @Description: 
 * @Author: summer
 * @CreateDate: 2025/4/17 10:43
 * @Version: 1.0.0
 */
public class WebMvcUtil {

    private static final Logger logger = LoggerUtil.COMMON_DEFAULT;

    private final static Pattern PATTERN = Pattern.compile("(\\d+\\.)+\\d+");

    public static void printResult(HttpServletResponse response, Object result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        printWriter.flush();
    }
}
