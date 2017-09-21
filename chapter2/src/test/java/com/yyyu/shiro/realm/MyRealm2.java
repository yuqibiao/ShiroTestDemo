package com.yyyu.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * 功能：自定义realm
 *
 * @author yu
 * @date 2017/9/21.
 */
public class MyRealm2 implements Realm{

    @Override
    public String getName() {
        return "myRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        //只能强转为char[]
        String password = new String((char[]) authenticationToken.getCredentials());
        if (!"yyyu".equals(username)){
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)){
            throw  new IncorrectCredentialsException();
        }
        //如果身份验证成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username , password , getName());
    }
}
