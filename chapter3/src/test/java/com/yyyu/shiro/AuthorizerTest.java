package com.yyyu.shiro;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 功能：授权
 *
 * @author yu
 * @date 2017/9/21.
 */
public class AuthorizerTest extends BaseTest{

    @Test
    public void testIsPermitted(){
        login("classpath:shiro-authorizer.ini" , "yu" , "123");
        Subject subject = getSubject();
        //直接解析权限（BitAndWildPermissionResolver）
        boolean permitted1 = subject.isPermitted("+user1+2");
        boolean permitted2 = subject.isPermitted("+user1+8");
        boolean permitted3 = subject.isPermitted("+user1+10");
        boolean permitted4 = subject.isPermitted("+user1+4");

        //通过Role解析权限（MyRolePermissionResolver）
        boolean permitted5 = subject.isPermitted("menu:delete");
        boolean permitted6 = subject.isPermitted("+user2+1");

        log("permitted1："+permitted1);
        log("permitted2："+permitted2);
        log("permitted3："+permitted3);
        log("permitted4："+permitted4);
        log("permitted5："+permitted5);
        log("permitted6："+permitted6);
    }

    @Test
    public void testIsPermittedJdbc(){
        login("classpath:shiro-jdbc-authorizer.ini" , "yu" , "123");
        Subject subject = getSubject();
        //直接解析权限（BitAndWildPermissionResolver）
        boolean permitted1 = subject.isPermitted("+user1+2");
        boolean permitted2 = subject.isPermitted("+user1+8");
        boolean permitted3 = subject.isPermitted("+user1+10");
        boolean permitted4 = subject.isPermitted("+user1+4");

        //通过Role解析权限（MyRolePermissionResolver）
        boolean permitted5 = subject.isPermitted("menu:delete");
        boolean permitted6 = subject.isPermitted("+user2+1");

        log("permitted1："+permitted1);
        log("permitted2："+permitted2);
        log("permitted3："+permitted3);
        log("permitted4："+permitted4);
        log("permitted5："+permitted5);
        log("permitted6："+permitted6);
    }

}
