package com.xavier.config.shiro;

import lombok.AllArgsConstructor;
import org.apache.shiro.session.Session;

import java.io.Serializable;

@AllArgsConstructor
public class SessionIdGenerator implements org.apache.shiro.session.mgt.eis.SessionIdGenerator {
    private String prefix;

    @Override
    public Serializable generateId(Session session) {
        return prefix + session.getId();
    }
}
