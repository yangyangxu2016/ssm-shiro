package com.zhuoyue.service.impl;

import com.zhuoyue.dao.T_userDao;
import com.zhuoyue.pojo.T_user;
import com.zhuoyue.service.T_userService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author xyy
 * @create 2017-07-19 17:16
 **/
@Service("t_userService")
public class T_userServiceImpl implements T_userService {

    @Resource
    private T_userDao t_userDao ;

    @Override
    public T_user findUserByUsername(String username) {
        T_user t_user = t_userDao.findUserByUsername(username);
        return t_user;
    }

    @Override
    public Set<String> findRoles(String username) {
        return t_userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return t_userDao.findPermissions(username);
    }

    @Override
    public T_user selectByPrimaryKey(int userId) {
        return t_userDao.selectByPrimaryKey(userId);
    }
}
