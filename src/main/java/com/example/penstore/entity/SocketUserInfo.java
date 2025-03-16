package com.example.penstore.entity;


//在宋铁老师的视频里，说这个domain的包名在springboot里面最好要改成entity,要不要改一下呢
import org.springframework.stereotype.Component;

import jakarta.websocket.Session;
@Component

public class SocketUserInfo {
    //用户sessionId
    private String sessionId;

    //用户session
    private Session session;

    //目标用户sessionid
    private String targetSessionId;

    //用户角色
    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getTargetSessionId() {
        return targetSessionId;
    }

    public void setTargetSessionId(String targetSessionId) {
        this.targetSessionId = targetSessionId;
    }
}


