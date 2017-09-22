package com.yyyu.shiro.permission;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * 功能：自定义permission
 * 实现Permission接口，重写implies方法，返回true表示有权限，false表示无权限
 *
 * 权限字符串格式：
 * +资源字符串+权限位+实例 ID；以+开头 中间通过+分割；权限：0 表示所有权限；
 * 1 新增（二进制：0001）、2 修改（二进制：0010）、4 删除（二进制：0100）、
 * 8 查看（二进制：1000）；如 +user+10 表示对资源 user 拥有修改/查看权限。
 * @author yu
 * @date 2017/9/22.
 */
public class BitPermission implements Permission {

    private String resourceIdentify;
    private int permissionBit;
    private String instanceId;

    public BitPermission(String permissionStr) {
        String[] array = permissionStr.split("\\+");
        if(array.length > 1) {
            resourceIdentify = array[1];
        }

        if(StringUtils.isEmpty(resourceIdentify)) {
            resourceIdentify = "*";
        }

        if(array.length > 2) {
            permissionBit = Integer.valueOf(array[2]);
        }

        if(array.length > 3) {
            instanceId = array[3];
        }

        if(StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }
    }

    @Override
    public boolean implies(Permission permission) {
        if (! (permission instanceof BitPermission)){
            return false;
        }
        //---在realm中添加的权限
        BitPermission realmPermission = (BitPermission) permission;
        if(!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(realmPermission.resourceIdentify))) {
            return false;
        }

        if(!(this.permissionBit ==0 || (this.permissionBit & realmPermission.permissionBit) != 0)) {
            return false;
        }

        if(!("*".equals(this.instanceId) || this.instanceId.equals(realmPermission.instanceId))) {
            return false;
        }
        return true;
    }


}
