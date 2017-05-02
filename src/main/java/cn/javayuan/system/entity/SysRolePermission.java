package cn.javayuan.system.entity;

import javax.persistence.*;

@Table(name = "sys_role_permission")
public class SysRolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "sys_role_id")
    private Integer sysRoleId;

    /**
     * 权限id
     */
    @Column(name = "sys_permission_id")
    private Integer sysPermissionId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色id
     *
     * @return sys_role_id - 角色id
     */
    public Integer getSysRoleId() {
        return sysRoleId;
    }

    /**
     * 设置角色id
     *
     * @param sysRoleId 角色id
     */
    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    /**
     * 获取权限id
     *
     * @return sys_permission_id - 权限id
     */
    public Integer getSysPermissionId() {
        return sysPermissionId;
    }

    /**
     * 设置权限id
     *
     * @param sysPermissionId 权限id
     */
    public void setSysPermissionId(Integer sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }
}