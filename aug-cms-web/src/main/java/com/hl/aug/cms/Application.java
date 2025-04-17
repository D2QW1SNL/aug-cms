package com.hl.aug.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 启动类
 * @Author: summer
 * @CreateDate: 2023/1/16 15:44
 * @Version: 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.hl"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
