package com.qyy.mqconsumer.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;


@XStreamAlias("mq")
public class MqInterface implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
