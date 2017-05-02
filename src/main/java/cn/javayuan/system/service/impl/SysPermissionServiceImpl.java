/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.service.impl;

import cn.javayuan.system.dto.UseUserDto;
import cn.javayuan.system.dto.UseMenuDto;
import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.entity.SysRolePermission;
import cn.javayuan.system.mapper.SysPermissionMapper;
import cn.javayuan.system.mapper.SysRolePermissionMapper;
import cn.javayuan.system.service.SysPermissionService;
import cn.javayuan.system.utils.ShiroUtil;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *  权限 菜单业务处理实现
 * Created by 李明元 on 2016/12/9.
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    /**
     * 获取用户菜单json
     *
     * @return
     */
    @Override
    public String getMenuZtreeJson() {
        UseUserDto useUserDto = ShiroUtil.getSessionActiveUser();
        List<SysPermission> menuList = new ArrayList<SysPermission>();
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        menuList = sysPermissionMapper.selectMenuByUserRole(useUserDto.getSysUser().getId());
        for (SysPermission m : menuList) {
            Map<String, String> stringStringMap = new LinkedHashMap<String, String>();
            stringStringMap.put("id", String.valueOf(m.getId()));
            stringStringMap.put("pId", String.valueOf(m.getParentid()));
            stringStringMap.put("name", m.getName());
            if (m.getParentid() == 0) {
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
     * 获取用户对应的菜单和子菜单包含的用户权限
     *
     * @return
     */
    @Override
    public List<SysPermission> findTopTypeMenuByUserRole() {
        return sysPermissionMapper.selectTopMenuByUserRole(ShiroUtil.getSessionActiveUser().getSysUser().getId());
    }

    /**
     * 获取用户对应的子菜单和子菜单权限
     *
     * @return
     */
    @Override
    public Map<SysPermission, List<SysPermission>> getNotTopMenuAndPermissionByUserRole() {
        Map<SysPermission, List<SysPermission>> sysPermissionListMap = new LinkedHashMap<>();
        List<SysPermission> sysMenus = sysPermissionMapper.selectNotTopMenuByUserRole(ShiroUtil.getSessionActiveUser().getSysUser().getId());
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
        return sysPermissionListMap;
    }


    /**
     * 保存菜单
     *
     * @param permission
     * @return
     */
    @Override
    public boolean savePermission(SysPermission permission) {
        if (permission.getType() == 0) {
            permission.setParentid(0);
        }
        int rs = sysPermissionMapper.insert(permission);
        if (rs > 0) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setSysRoleId(ShiroUtil.getSessionActiveUser().getSysRole().getId());
            sysRolePermission.setSysPermissionId(permission.getId());
            int rss = sysRolePermissionMapper.insert(sysRolePermission);
            if (rss > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteMenuById(int id) {
        try {
            deleteMenuByIdTo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取菜单和菜单对应的权限
     * @param id
     * @return
     */
    @Override
    public UseMenuDto findMenuAndPermission(int id) {
        UseMenuDto useMenuDto = new UseMenuDto();
        SysPermission menu=sysPermissionMapper.selectByPrimaryKey(id);
        useMenuDto.setMenu(menu);
        if (menu.getType()==1){
            List<SysPermission> sysPermissions = sysPermissionMapper.selectPermissionListByUserIdAndRoleId(menu.getId(),ShiroUtil.getSessionActiveUser().getSysDept().getId());
            useMenuDto.setPermissionList(sysPermissions);
            useMenuDto.setTopMenu(sysPermissionMapper.selectByPrimaryKey(menu.getParentid()).getName());
        }
        return useMenuDto;
    }

    /**
     * 递归删除菜单
     * @param id
     */
    private void deleteMenuByIdTo(int id) {
        Example example = new Example(SysRolePermission.class);
        example.createCriteria().andEqualTo("sysPermissionId", id);
        sysRolePermissionMapper.deleteByExample(example);
        sysPermissionMapper.deleteByPrimaryKey(id);
        Example example2 = new Example(SysPermission.class);
        example2.createCriteria().andEqualTo("parentid", id);
        List<SysPermission> sysPermissions = sysPermissionMapper.selectByExample(example2);
        if (sysPermissions != null && sysPermissions.size() > 0) {
            for (SysPermission permission : sysPermissions) {
                deleteMenuByIdTo(permission.getId());
            }
        }
    }
}

