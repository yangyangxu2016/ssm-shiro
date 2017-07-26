package com.zhuoyue.controller;

import com.zhuoyue.pojo.T_user;
import com.zhuoyue.service.T_userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xyy
 * @create 2017-07-19 17:17
 **/
@Controller
public class T_userController {


    @Resource
    private T_userService t_userService;


    /**
     * get 请求主页
     * @return
     */
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.GET)
    public String login() {
        return "../../login";
    }


    /**
     * 主要就是login()方法。逻辑比较简单，只是登录验证的时候不是像之前那样直接查询数据库然后返回是否有用户了，
     * 而是调用subject的login()方法,就是我上面提到的，调用login()方法时Shiro会自动调用我们自定义的MyRealm类中的doGetAuthenticationInfo()方法进行验证的，
     * 验证逻辑是先根据用户名查询用户，如果查询到的话再将查询到的用户名和密码放到SimpleAuthenticationInfo对象中，
     * Shiro会自动根据用户输入的密码和查询到的密码进行匹配，如果匹配不上就会抛出异常，匹配上之后就会执行doGetAuthorizationInfo()进行相应的权限验证。
     */
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    public String login(T_user user, Model model) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword()) ;
        try {
            subject.login(token);
            return "admin";
        } catch (Exception e) {
            //这里将异常打印关闭是因为如果登录失败的话会自动抛异常
//            e.printStackTrace();
            model.addAttribute("error", "用户名或密码错误");
            return "../../login";
        }


    }


    /**
     * 登录成功后放入User对象
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/first")
    public String first(Model model)throws Exception{

        //从shiro的session中取出activeUser
        Subject subject= SecurityUtils.getSubject();
        //取出身份信息
        T_user activeUser= (T_user) subject.getPrincipal();
        //通过model传到页面
        model.addAttribute("activeUser",activeUser);

        return "/first";
    }




    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/student")
    public String student() {
        return "admin";
    }

    @RequestMapping("/teacher")
    public String teacher() {
        return "admin";
    }


    @RequestMapping(value = "/queryName", method = RequestMethod.GET)
    public String testHelloWorld() {

        T_user u = t_userService.findUserByUsername("aaa");
        System.out.println(u.getUserName());
        System.out.println(u.getPassword());
        System.out.println("-------------------");

        Set set = t_userService.findRoles("aaa");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            System.out.println("-------------------");
        }

        Set set1 = t_userService.findPermissions("aaa");
        Iterator iterator1 = set.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
            System.out.println("-------------------");
        }
        return "Hello,World";

    }


}
