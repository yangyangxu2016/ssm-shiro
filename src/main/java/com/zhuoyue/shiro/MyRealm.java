package com.zhuoyue.shiro;

import com.zhuoyue.pojo.T_user;
import com.zhuoyue.service.T_userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author xyy
 * @create 2017-07-19 19:10
 **/
public class MyRealm extends AuthorizingRealm {


    @Resource
    private T_userService t_userService;


    /**
     * @Author xuyangyang
     * @Describe 用于权限的验证
     * 根据用户名获取到他所拥有的角色以及权限，
     * 然后赋值到SimpleAuthorizationInfo对象中即可，Shiro就会按照我们配置的XX角色对应XX权限来进行判断
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        T_user user = (T_user) principalCollection.getPrimaryPrincipal();
        Set<String> roles = t_userService.findRoles(user.getUserName());
//        模拟从数据库中获取到的动态权限数据
        Set<String> permissions = t_userService.findPermissions((user.getUserName()));
        //查到权限数据，返回授权信息(包括上边的permissions)
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }


    /**
     * 登录验证,在登录的时候需要将数据封装到Shiro的一个token中，执行shiro的login()方法，
     * 之后只要我们将MyRealm这个类配置到Spring中，登录的时候Shiro就会自动的调用doGetAuthenticationInfo()方法进行验证。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
//        获取用户账号
        T_user user = t_userService.findUserByUsername(username);
        String saleId = String.valueOf(user.getId());
//        password = MD5Util.MD5(password);
//        password = MD5Util.MD5(password + userInfo.getId())

        if (user != null) {
            ////将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数随便放一个就行了
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),getName());
            return authenticationInfo;
        } else {
            return null;
        }
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
