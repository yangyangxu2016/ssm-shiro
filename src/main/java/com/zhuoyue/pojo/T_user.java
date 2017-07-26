package com.zhuoyue.pojo;

/**
 * @author xyy
 * @create 2017-07-19 16:48
 **/
public class T_user {

    private Integer id;
    private String userName;
    private String password;
    private Integer roleId ;

    public Integer getId() {
        return id;
    }

    public T_user setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public T_user setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public T_user setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public T_user setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }
}
