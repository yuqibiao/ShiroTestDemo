package com.yyyu.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/9/21.
 */
public class LoginLogoutTest {


    @Test
    public void testAutSuccessfulStrategy(){
        //1.得到SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authenticator-successful-strategy.ini");
        //2.得到SecurityManager实例并设置SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject和创建Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yu", "123");
        try {
            //4.身份验证成功
            subject.login(token);
            PrincipalCollection previousPrincipals = subject.getPrincipals();
            log("验证" + subject.isAuthenticated()+
                    "previousPrincipals.asList()="+previousPrincipals.asList());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //5.身份验证失败(抛出异常UnknownAccountException)
        }
        //subject.logout();
        log("验证" + subject.isAuthenticated());
    }

    @Test
    public void testJDBCRealm(){
        //1.得到SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        //2.得到SecurityManager实例并设置SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject和创建Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yu", "123");
        try {
            //4.身份验证成功
            subject.login(token);
            log("验证" + subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //5.身份验证失败(抛出异常UnknownAccountException)
        }
        //subject.logout();
        log("验证" + subject.isAuthenticated());
    }

    @Test
    public void testMultiCustomRealm(){
        //1.得到SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-custom-realm-multi.ini");
        //2.得到SecurityManager实例并设置SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject和创建Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yu", "123");
        try {
            //4.身份验证成功
            subject.login(token);
            //log("验证" + subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //5.身份验证失败(抛出异常UnknownAccountException)
        }
        //subject.logout();
        log("验证" + subject.isAuthenticated());
    }

    @Test
    public void testCustomRealm(){
        //1.得到SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-custom-realm.ini");
        //2.得到SecurityManager实例并设置SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject和创建Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yu", "123");
        try {
            //4.身份验证成功
            subject.login(token);
           // log("验证" + subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //5.身份验证失败(抛出异常UnknownAccountException)
        }
        subject.logout();
        //log("验证" + subject.isAuthenticated());
    }

    @Test
    public void testHelloWord() {
        //1.得到SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.得到SecurityManager实例并设置SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject和创建Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4.身份验证成功
            subject.login(token);
            log("验证" + subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //5.身份验证失败(抛出异常UnknownAccountException)
        }
        subject.logout();
        log("验证" + subject.isAuthenticated());
    }

    private void log(String str) {
        System.out.println(str);
    }

}
