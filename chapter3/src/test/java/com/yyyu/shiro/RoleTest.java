package com.yyyu.shiro;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 功能：用户角色测试
 *
 * @author yu
 * @date 2017/9/21.
 */
public class RoleTest extends BaseTest{

    @Test
    public void testIsPermission(){
        login("classpath:shiro-permission.ini" , "yu" , "123");
        Subject subject = getSubject();
        boolean permitted = subject.isPermitted("user:insert");
        log("permitted："+permitted);
        boolean permittedAll = subject.isPermittedAll("user:insert", " user:create");
        log("permittedAll："+permittedAll);
        //没有permission会抛出异常（UnauthorizedException）
        subject.checkPermission("user:insert");
    }

    @Test
    public void testCheckRole(){
        login("classpath:shiro-role.ini" , "yu" , "123");
        Subject subject = getSubject();
        //和hasRole的区别是没有该role会抛出异常（UnauthorizedException）
        subject.checkRole("role1");
    }

    @Test
    public void testHasRole(){
        login("classpath:shiro-role.ini" , "yu" , "123");
        Subject subject = getSubject();
        boolean hasRole1 = subject.hasRole("role1");
        log("hasRole1："+hasRole1);
    }

}
