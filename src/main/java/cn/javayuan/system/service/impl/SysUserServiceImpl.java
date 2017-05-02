/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.service.impl;

import cn.javayuan.system.dto.UseUserDto;
import cn.javayuan.system.entity.*;
import cn.javayuan.system.mapper.*;
import cn.javayuan.system.service.SysUserService;
import cn.javayuan.system.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户业务处理实现
 * Created by 李明元 on 2016/12/9.
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    @Autowired
    private SysUserDeptMapper sysUserDeptMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 通过组织机构id获取全部用户数据
     *
     * @param id
     * @return
     */
    @Override
    public List<SysUser> findSysUserByDeptId(int id) {
        return sysUserMapper.selectSysUserByDeptId(id);
    }

    /**
     * 保存用户
     *
     * @param user   用户
     * @param deptid 组织机构id
     * @param roleid 角色id
     * @return
     */
    @Override
    public boolean saveSysUser(SysUser user, int deptid, int roleid) {
        user.setSalt(ShiroUtil.createSalt());
        user.setPassword(ShiroUtil.createEncodedPassword(user.getPassword(), user.getSalt()));
        int rs = sysUserMapper.insert(user);
        if (rs > 0) {
            if (user.getId() != null && user.getId() > 0) {
                if (deptid > 0) {
                    SysUserDept sysUserDept = new SysUserDept();
                    sysUserDept.setSysUserId(user.getId());
                    sysUserDept.setSysDeptId(deptid);
                    int rss = sysUserDeptMapper.insert(sysUserDept);
                    if (rss > 0) {
                        if (roleid > 0) {
                            SysUserRole sysUserRole = new SysUserRole();
                            sysUserRole.setSysUserId(user.getId());
                            sysUserRole.setSysRoleId(roleid);
                            sysUserRoleMapper.insert(sysUserRole);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 更新用户
     *
     * @param user   用户信息
     * @param roleid 用户角色id
     * @return 是否
     */
    @Override
    public boolean updateUserAndRole(SysUser user, int roleid, int deptid) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(user.getId());
        if (!user.getPassword().equals("")) {
            user.setPassword(ShiroUtil.createEncodedPassword(user.getPassword(), sysUser.getSalt()));
        } else {
            user.setPassword(sysUser.getPassword());
        }
        user.setSalt(sysUser.getSalt());
        int rs = sysUserMapper.updateByPrimaryKey(user);
        if (rs > 0) {
            Example example = new Example(SysUserRole.class);
            example.createCriteria().andEqualTo("sysUserId", user.getId());
            SysUserRole sysUserRole = sysUserRoleMapper.selectByExample(example).get(0);
            if (roleid != sysUserRole.getSysRoleId()) {
                sysUserRole.setSysRoleId(roleid);
                sysUserRoleMapper.updateByPrimaryKey(sysUserRole);
            }
            Example example1 = new Example(SysUserDept.class);
            example1.createCriteria().andEqualTo("sysUserId", user.getId());
            SysUserDept sysUserDept = sysUserDeptMapper.selectByExample(example1).get(0);
            if (deptid != sysUserDept.getSysDeptId()) {
                sysUserDept.setSysDeptId(deptid);
                sysUserDeptMapper.updateByPrimaryKey(sysUserDept);
            }
            return true;
        }
        return false;
    }

    /**
     * 删除用户操作
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserById(int id) {
        int rs = sysUserMapper.deleteByPrimaryKey(id);
        if (rs > 0) {
            Example example = new Example(SysUserDept.class);
            example.createCriteria().andEqualTo("sysUserId", id);
            sysUserDeptMapper.deleteByExample(example);
            Example example1 = new Example(SysUserRole.class);
            example1.createCriteria().andEqualTo("sysUserId", id);
            sysUserRoleMapper.deleteByExample(example1);
            return true;
        }

        return false;
    }

    /**
     * 通过用户id查询用户对应的权限
     *
     * @param id
     * @return
     */
    @Override
    public List<SysPermission> findPermissionListByUserId(int id) {
        return sysPermissionMapper.selectPermissionListByUserId(id);
    }


    /**
     * 通过用户名查询用户
     *
     * @param userCode
     * @return
     */
    @Override
    public SysUser findSysUserByUserCode(String userCode) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("usercode", userCode);
        List<SysUser> sysUsers =sysUserMapper.selectByExample(example);
        if(sysUsers.size()>0){
            return sysUsers.get(0);
        }
        return null;
    }

    /**
     * 通过用户id获取角色
     *
     * @param id
     * @return
     */
    @Override
    public SysRole findRoleByUserId(int id) {
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andEqualTo("sysUserId", id);
        return sysRoleMapper.selectByPrimaryKey(sysUserRoleMapper.selectByExample(example).get(0).getSysRoleId());
    }

    /**
     * 通过用户id获取组织机构
     *
     * @param id
     * @return
     */
    @Override
    public SysDept findDeptByUserId(int id) {
        Example example = new Example(SysUserDept.class);
        example.createCriteria().andEqualTo("sysUserId", id);
        return sysDeptMapper.selectByPrimaryKey(sysUserDeptMapper.selectByExample(example).get(0).getSysDeptId());
    }

    /**
     * 获取用户对应的菜单，包含主菜单和子菜单
     *
     * @param id
     * @return
     */
    @Override
    public Map<SysPermission, List<SysPermission>> getUserMapMenu(int id) {
        Map<SysPermission, List<SysPermission>> mapMenu = new LinkedHashMap<>();
        List<SysPermission> userTopMenu;
        userTopMenu = sysPermissionMapper.selectTopMenuByUserRole(id);
        for (SysPermission menu : userTopMenu) {
            List<SysPermission> userMenu;
            userMenu = sysPermissionMapper.selectNotTopMenuByUserRoleAndTopMenu(id, menu.getId());
            mapMenu.put(menu, userMenu);
        }
        return mapMenu;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(SysUser user) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(user.getId());
        if(!user.getPassword().equals("")){
            user.setPassword(ShiroUtil.createEncodedPassword(user.getPassword(),sysUser.getSalt()));
        }else {
            user.setPassword(sysUser.getPassword());
        }
        user.setSalt(sysUser.getSalt());
        int rs =sysUserMapper.updateByPrimaryKey(user);
        if(rs>0){
            ShiroUtil.clearCached();
            return true;
        }
        return false;
    }

    @Override
    public List<UseUserDto> findUserByDeptId(int id) {
        List<UseUserDto> useUserDtos = new ArrayList<>();
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(id);
        List<SysUser> sysUsers = sysUserMapper.selectSysUserByDeptId(id);
        for (SysUser user:sysUsers){
            UseUserDto userDto = new UseUserDto();
            userDto.setSysUser(user);
            userDto.setSysDept(sysDept);
            SysRole sysRole =sysRoleMapper.selectRoleByUserId(user.getId());
            userDto.setSysRole(sysRole);
            useUserDtos.add(userDto);
        }
        return useUserDtos;
    }

    @Override
    public UseUserDto findSysUserById(int id) {
        SysUser sysUser =sysUserMapper.selectByPrimaryKey(id);
        SysDept sysDept=sysDeptMapper.selectDeptByUserId(id);
        SysRole sysRole =sysRoleMapper.selectRoleByUserId(id);
        UseUserDto userDto = new UseUserDto();
        userDto.setSysUser(sysUser);
        userDto.setSysRole(sysRole);
        userDto.setSysDept(sysDept);
        return userDto;
    }
}
