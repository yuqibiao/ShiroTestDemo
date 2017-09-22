package com.yyyu.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/9/22.
 */
public class BitAndWildPermissionResolver implements PermissionResolver{
    @Override
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith("+")){
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
