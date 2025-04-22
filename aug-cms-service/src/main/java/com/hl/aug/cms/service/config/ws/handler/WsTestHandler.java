package com.hl.aug.cms.service.config.ws.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.io.IOException;

/**
 * @Description: ws 是否可以成功链接
 * @Author: summer
 * @CreateDate: 2025/4/22 13:53
 * @Version: 1.0.0
 */
//@Component
//@ServerEndpoint(value = "/ws/test")
public class WsTestHandler {

    private static final Logger log = LoggerFactory.getLogger(WsTestHandler.class);

    @OnOpen
    public void onOpen(Session session) {
        log.info("🎉 WebSocket 连接建立！");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("收到消息: " + message);
        session.getBasicRemote().sendText("你发的是：" + message);

    }

    @OnClose
    public void onClose(Session session) {
        log.info("❌ WebSocket 关闭！");
    }

}
