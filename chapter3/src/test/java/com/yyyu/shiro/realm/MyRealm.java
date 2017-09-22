package com.yyyu.shiro.realm;

import com.yyyu.shiro.permission.BitPermission;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/9/22.
 */
public class MyRealm extends AuthorizingRealm{

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("admin");
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       String username = (String) authenticationToken.getPrincipal();//用户名
        String password = new String((char[])authenticationToken.getCredentials()); //得到密码
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        if (!"yu".equals(username)){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(username , password , getName());
    }
}
