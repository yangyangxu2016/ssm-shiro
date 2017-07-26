package com.zhuoyue.dao;

import com.zhuoyue.pojo.T_user;

import java.util.Set;

/**
 * Created by 14258 on 2017/7/19.
 */
public interface T_userDao {


    T_user findUserByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    T_user selectByPrimaryKey(int userId) ;
}
