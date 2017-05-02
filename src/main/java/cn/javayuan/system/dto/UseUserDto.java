package cn.javayuan.system.dto;

import cn.javayuan.system.entity.SysDept;
import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.entity.SysRole;
import cn.javayuan.system.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 活动一用户->>session中存储的用户数据封装
 * Created by 李明元 on 2016/11/22.
 */
public class UseUserDto implements java.io.Serializable {
    private SysUser sysUser; //用户
    private SysRole sysRole; //角色
    private SysDept sysDept;    //组织机构
    private Map<SysPermission, List<SysPermission>> menus;//key主 菜单，value子菜单
    private List<SysPermission> permissions;// 权限

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Map<SysPermission, List<SysPermission>> getMenus() {
        return menus;
    }

    public void setMenus(Map<SysPermission, List<SysPermission>> menus) {
        this.menus = menus;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }
}
