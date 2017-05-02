/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.service.impl;

import cn.javayuan.system.dto.UseRoleDto;
import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.entity.SysRole;
import cn.javayuan.system.entity.SysRolePermission;
import cn.javayuan.system.entity.SysUserRole;
import cn.javayuan.system.mapper.*;
import cn.javayuan.system.service.SysRoleService;
import cn.javayuan.system.utils.ShiroUtil;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户角色业务处理实现
 * Created by 李明元 on 2016/12/9.
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    /**
     * 获取用户拥有的角色
     *
     * @return
     */
    @Override
    public List<SysRole> findRoleListByUser() {
        int nowRoleId = ShiroUtil.getSessionActiveUser().getSysRole().getId();
        Example example = new Example(SysRole.class);
        example.createCriteria().andEqualTo("parentid", nowRoleId);
        example.orderBy("sort");
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        sysRoles.add(ShiroUtil.getSessionActiveUser().getSysRole());
        return sysRoles;
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
     * 获取用户对应的角色树
     *
     * @return
     */
    @Override
    public String getRoleZtreeJson() {
        List<SysRole> roleList = new ArrayList<SysRole>();
        roleList = findRoleByUser();
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        for (SysRole role : roleList) {
            Map<String, String> stringStringMap = new LinkedHashMap<String, String>();
            stringStringMap.put("id", String.valueOf(role.getId()));
            stringStringMap.put("pId", String.valueOf(role.getParentid()));
            stringStringMap.put("name", role.getName());
            if (role.getParentid() == 0 || findRoleByParentid(role.getId()).size() > 0) {
                stringStringMap.put("open", "true");
                stringStringMap.put("iconOpen", "lib/zTree/v3/css/zTreeStyle/img/diy/1_open.png");
                stringStringMap.put("iconClose", "lib/zTree/v3/css/zTreeStyle/img/diy/1_close.png");
            } else {
                stringStringMap.put("icon", "lib/zTree/v3/css/zTreeStyle/img/diy/2.png");
            }
            mapList.add(stringStringMap);
        }
        return JSONUtils.toJSONString(mapList);
    }

    /**
     * 通过角色id删除角色，并且需要删除相关表和角色下的相关用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteRoleById(int id) {
        try {
            deleteRoleByIdTo(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取角色已拥有的权限id
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findRoleHasPermissionIds(int id) {
        List<Integer> integerList = new ArrayList<Integer>();
        Example example = new Example(SysRolePermission.class);
        example.createCriteria().andEqualTo("sysRoleId", id);
        List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectByExample(example);
        if (sysRolePermissions != null && sysRolePermissions.size() > 0) {
            for (SysRolePermission rolePermission : sysRolePermissions) {
                integerList.add(rolePermission.getSysPermissionId());
            }
        }
        return integerList;
    }

    /**
     * 获取用应的全部菜单和权限
     * @return
     */
    @Override
    public Map<List<SysPermission>, Map<SysPermission, List<SysPermission>>> getMenuAndPermissionByUserRole() {
        int userid=ShiroUtil.getSessionActiveUser().getSysUser().getId();
        Map<SysPermission, List<SysPermission>> sysPermissionListMap = new LinkedHashMap<>();
        List<SysPermission> sysMenus = sysPermissionMapper.selectNotTopMenuByUserRole(userid);
        for (SysPermission menu : sysMenus) {
            Example example1 = new Example(SysPermission.class);
            example1.createCriteria().andEqualTo("parentid", menu.getId());
            example1.createCriteria().andEqualTo("type", 2);
            example1.orderBy("sort").asc();
            List<SysPermission> sysPermissions = sysPermissionMapper.selectByExample(example1);
            if (sysPermissions != null && sysPermissions.size() > 0) {
                sysPermissionListMap.put(menu, sysPermissions);
            }
        }
        List<SysPermission> sysPermissions = new ArrayList<>();
        sysPermissions=sysPermissionMapper.selectTopMenuByUserRole(userid);
        Map<List<SysPermission>, Map<SysPermission, List<SysPermission>>> listMapMap = new LinkedHashMap<>();
        listMapMap.put(sysPermissions,sysPermissionListMap);
        return listMapMap;
    }

    /**
     * 保存角色权限
     *
     * @param roleid
     * @param permissionid
     * @return
     */
    @Override
    public boolean saveRolePermission(int roleid, String permissionid) {
        if (!permissionid.equals("")) {
            Example example= new Example(SysRolePermission.class);
            example.createCriteria().andEqualTo("sysRoleId",roleid);
            sysRolePermissionMapper.deleteByExample(example);
            for (String pid : permissionid.split(",")) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setSysRoleId(Integer.valueOf(roleid));
                sysRolePermission.setSysPermissionId(Integer.valueOf(pid));
                sysRolePermissionMapper.insert(sysRolePermission);
            }
            ShiroUtil.clearCached();
            return true;
        }
        return false;
    }

    /**
     * 获取角色dto
     * @param id
     * @return
     */
    @Override
    public UseRoleDto findRoleById(int id) {
        UseRoleDto useRoleDto = new UseRoleDto();
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        useRoleDto.setRole(sysRole);
        if(sysRole.getParentid()!=0){
            useRoleDto.setTopRole(sysRoleMapper.selectByPrimaryKey(sysRole.getParentid()).getName());
        }
        return useRoleDto;
    }

    /**
     * 递归删除角色
     * @param id
     */
    private void deleteRoleByIdTo(int id) {

        sysUserMapper.deleteUserByRoleIds(id);

        Example example = new Example(SysUserRole.class);
        example.createCriteria().andEqualTo("sysRoleId", id);
        sysUserRoleMapper.deleteByExample(example);


        Example example1 = new Example(SysRolePermission.class);
        example1.createCriteria().andEqualTo("sysRoleId", id);
        sysRolePermissionMapper.deleteByExample(example1);

        sysRoleMapper.deleteByPrimaryKey(id);

        Example example2 = new Example(SysRole.class);
        example2.createCriteria().andEqualTo("parentid", id);
        List<SysRole> oldRoles = sysRoleMapper.selectByExample(example2);
        if (oldRoles != null && oldRoles.size() > 0) {
            for (SysRole sysRole1 : oldRoles) {
                deleteRoleByIdTo(sysRole1.getId());
            }
        }
    }
    /**
     * 获取用户对应的全部角色和子角色
     *
     * @return
     */
    private List<SysRole> findRoleByUser() {
        SysRole role = sysRoleMapper.selectByPrimaryKey(ShiroUtil.getSessionActiveUser().getSysRole().getId());
        List<Integer> integerList = getAllRoleChildren(role.getId());
        List<SysRole> roleList = new ArrayList<SysRole>();
        for (int ii : integerList) {
            SysRole role1 = sysRoleMapper.selectByPrimaryKey(ii);
            roleList.add(role1);
        }
        return roleList;

    }

    /**
     * 存放获取到的角色子节点
     */
    private List<Integer> roleList = null;

    /**
     * 获取 角色子节点
     *
     * @param pid
     * @return
     */
    private List<Integer> getAllRoleChildren(int pid) {
        roleList = new ArrayList<Integer>();
        roleList.add(pid);
        return getRoleChildren(pid);
    }

    /**
     * 获取 角色子节点
     *
     * @param pid
     * @return
     */
    private List<Integer> getRoleChildren(int pid) {
        SysRole role = sysRoleMapper.selectByPrimaryKey(pid);
        if (findRoleByParentid(role.getId()).size() > 0) {
            for (SysRole role1 : findRoleByParentid(role.getId())) {
                roleList.add(role1.getId());
                getRoleChildren(role1.getId());
            }
        }
        return roleList;
    }
    /**
     * 通过parentid查询角色信息
     *
     * @param roleid
     * @return
     */
    private List<SysRole> findRoleByParentid(int roleid) {
        Example example = new Example(SysRole.class);
        example.createCriteria().andEqualTo("parentid", roleid);
        example.orderBy("sort").asc();
        return sysRoleMapper.selectByExample(example);
    }
}
