package com.yyyu.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/9/22.
 */
public class MyRolePermissionResolver  implements RolePermissionResolver{

    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if("role1".equals(roleString)) {
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));
        }
        if ("admin".equals(roleString)){
            Collection<Permission> permissions = new ArrayList<Permission>();
            permissions.add(new BitPermission("+user2:1"));
            permissions.add(new BitPermission("+user3:2"));
            permissions.add(new BitPermission("+user4:4"));
        }
        return null;
    }

}
