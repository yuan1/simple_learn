/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.service;

import cn.javayuan.system.dto.UseUserDto;
import cn.javayuan.system.entity.SysDept;
import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.entity.SysRole;
import cn.javayuan.system.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 系统用户业务处理
 * Created by 李明元 on 2016/12/9.
 */
public interface SysUserService extends BaseService<SysUser> {
    //通过组织机构id获取用户
    List<SysUser> findSysUserByDeptId(int id);
    //保存用户
    boolean saveSysUser(SysUser user, int deptid, int roleid);

    //更新用户
    boolean updateUserAndRole(SysUser user, int roleid, int deptid);

    //删除用户操作
    boolean deleteUserById(int id);

    //通过用户id查询用户对应的权限
    List<SysPermission> findPermissionListByUserId(int id);

    //通过用户名查询用户
    SysUser findSysUserByUserCode(String userCode);

    //通过用户id获取角色
    SysRole findRoleByUserId(int id);

    //通过用户id获取组织机构
    SysDept findDeptByUserId(int id);

    //获取用户对应的菜单，包含主菜单和子菜单
    Map<SysPermission,List<SysPermission>> getUserMapMenu(int id);

    boolean updateUser(SysUser user);

    List<UseUserDto> findUserByDeptId(int id);

    UseUserDto findSysUserById(int id);
}
