package com.hl.aug.cms.service.config.ws2;

import com.jcraft.jsch.*;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.InputStream;
import java.io.OutputStream;

public class SSHWebSocketHandler extends TextWebSocketHandler {

    private Session sshSession;
    private ChannelShell channel;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Thread readThread;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = "username";
        String password = "password";
        String host = "localhost";
        int port = 22;

        JSch jsch = new JSch();
        sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);

        // 跳过 hostKey 检查
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.connect();

        channel = (ChannelShell) sshSession.openChannel("shell");
        inputStream = channel.getInputStream();
        outputStream = channel.getOutputStream();
        channel.connect();

        // 启动线程读取远程终端数据
        readThread = new Thread(() -> {
            byte[] buffer = new byte[1024];
            int len;
            try {
                while ((len = inputStream.read(buffer)) != -1) {
                    session.sendMessage(new TextMessage(new String(buffer, 0, len)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        readThread.start();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String cmd = message.getPayload();
        if (outputStream != null) {
            outputStream.write(cmd.getBytes());
            outputStream.flush();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (readThread != null) {
            readThread.interrupt();
        }
        if (channel != null) {
            channel.disconnect();
        }
        if (sshSession != null) {
            sshSession.disconnect();
        }
    }
}
