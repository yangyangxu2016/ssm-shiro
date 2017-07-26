package com.zhuoyue.shiro;

import org.apache.shiro.authc.SimpleAuthenticationInfo;

/**
 * @author xyy
 * @create 2017-07-26 13:37
 **/
public class MySimpleAuthenticationInfo extends SimpleAuthenticationInfo {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public MySimpleAuthenticationInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }


    public MySimpleAuthenticationInfo(Object username, Object password,String userId,String realmName) {
        super(username,password,realmName);
        this.userId = userId;

    }
}
