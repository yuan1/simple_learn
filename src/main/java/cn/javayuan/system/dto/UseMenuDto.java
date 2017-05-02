/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.dto;

import cn.javayuan.system.entity.SysPermission;

import java.util.List;

/**
 * 使用的菜单dto
 * Created by 李明元 on 2016/12/10.
 */
public class UseMenuDto {
    private String topMenu; //父菜单
    private SysPermission menu;   //菜单
    private List<SysPermission> permissionList; //菜单对应权限

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public SysPermission getMenu() {
        return menu;
    }

    public void setMenu(SysPermission menu) {
        this.menu = menu;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }

    public UseMenuDto(String topMenu, SysPermission menu, List<SysPermission> permissionList) {
        this.topMenu = topMenu;
        this.menu = menu;
        this.permissionList = permissionList;
    }

    public UseMenuDto(SysPermission menu, List<SysPermission> permissionList) {
        this.menu = menu;
        this.permissionList = permissionList;
    }

    public UseMenuDto(SysPermission menu) {
        this.menu = menu;
    }

    public UseMenuDto() {
    }
}
