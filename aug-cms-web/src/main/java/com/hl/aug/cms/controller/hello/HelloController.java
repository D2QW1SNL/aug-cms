package com.hl.aug.cms.controller.hello;

import com.hl.aug.cms.response.WinSportBaseResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: hello
 * @Author: summer
 * @CreateDate: 2024/4/26 10:13
 * @Version: 1.0.0
 */
@RequestMapping("hello")
@RestController
public class HelloController {

    /**
     * hello
     *
     * @return
     */
    @GetMapping("")
    public WinSportBaseResp hello() {
        return WinSportBaseResp.ok("hello kaiLian");
    }
}
