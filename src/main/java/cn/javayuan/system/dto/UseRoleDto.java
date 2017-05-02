/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.dto;

import cn.javayuan.system.entity.SysRole;

/**
 * 使用的角色dto
 * Created by 李明元 on 2016/12/10.
 */
public class UseRoleDto {
    private String topRole; //父节点
    private SysRole role;   //角色

    public String getTopRole() {
        return topRole;
    }

    public void setTopRole(String topRole) {
        this.topRole = topRole;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }
}
