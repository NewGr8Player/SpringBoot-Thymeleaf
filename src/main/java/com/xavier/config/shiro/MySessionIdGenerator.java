package com.xavier.config.shiro;

import java.io.Serializable;
import java.util.StringJoiner;
import java.util.UUID;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

public class MySessionIdGenerator implements SessionIdGenerator {

    private String prefix;

    public MySessionIdGenerator(String prefix) {
        this.prefix = prefix;

    }

    @Override
    public Serializable generateId(Session session) {
        return new StringJoiner(":", prefix, session.getHost()).add(UUID.randomUUID().toString()).toString();
    }
}
