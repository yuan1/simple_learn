/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.service;

import cn.javayuan.system.dto.UseMenuDto;
import cn.javayuan.system.entity.SysPermission;

import java.util.List;
import java.util.Map;

/**
 * 权限 菜单业务处理
 * Created by 李明元 on 2016/12/9.
 */
public interface SysPermissionService  extends BaseService<SysPermission>{

    //获取用户菜单json
    String getMenuZtreeJson();
    //获取用户对应的菜单和子菜单包含的用户权限
    List<SysPermission> findTopTypeMenuByUserRole();

    //获取用户对应的子菜单和子菜单权限
    Map<SysPermission, List<SysPermission>> getNotTopMenuAndPermissionByUserRole();

    //保存菜单
    boolean savePermission(SysPermission menu);

    //删除菜单
    boolean deleteMenuById(int id);

    //获取菜单和权限
    UseMenuDto findMenuAndPermission(int id);
}
