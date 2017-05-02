/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.service;

import cn.javayuan.system.dto.UseRoleDto;
import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 用户角色业务处理接口
 * Created by 李明元 on 2016/12/9.
 */
public interface SysRoleService extends BaseService<SysRole> {
    //获取用户拥有的角色
    List<SysRole> findRoleListByUser();

    //通过用户id获取角色
    SysRole findRoleByUserId(int id);

    //获取用户对应的角色树
    String getRoleZtreeJson();

    //通过角色id删除角色，并且需要删除相关表和角色下的相关用户
    boolean deleteRoleById(int id);

    //获取角色已拥有的权限id
    List<Integer> findRoleHasPermissionIds(int id);

    //获取用应的全部菜单和权限
    Map<List<SysPermission>, Map<SysPermission, List<SysPermission>>> getMenuAndPermissionByUserRole();

    //保存角色权限
    boolean saveRolePermission(int roleid, String permissionid);

    UseRoleDto findRoleById(int id);
}
