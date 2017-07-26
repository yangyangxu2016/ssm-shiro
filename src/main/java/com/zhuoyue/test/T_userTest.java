package com.zhuoyue.test;

import com.zhuoyue.pojo.T_user;
import com.zhuoyue.service.T_userService;
import com.zhuoyue.service.impl.T_userServiceImpl;
import org.junit.Test;

/**
 * @author xyy
 * @create 2017-07-19 17:19
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class T_userTest {

//    @Resource
//    private T_userService t_userService;
//

    @Test
    public void findUserByUsername() {

        T_userService t_userService = new T_userServiceImpl();

        System.out.println(t_userService);

        T_user u = t_userService.findUserByUsername("aaa");
        System.out.println(u.getUserName());

    }


}
