package cn.javayuan.system.entity;

import javax.persistence.*;

@Table(name = "sys_config")
public class SysConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 备案号
     */
    private String icp;

    /**
     * 底部版权信息
     */
    private String copyright;

    /**
     * 描述
     */
    private String description;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 网站名称
     */
    private String title;

    /**
     * 菜单管理使用说明
     */
    @Column(name = "menu_info")
    private String menuInfo;

    /**
     * 角色管理使用说明
     */
    @Column(name = "role_info")
    private String roleInfo;

    /**
     * 组织机构管理使用说明
     */
    @Column(name = "dept_info")
    private String deptInfo;

    /**
     * 用户管理使用说明
     */
    @Column(name = "user_info")
    private String userInfo;

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
     * 获取备案号
     *
     * @return icp - 备案号
     */
    public String getIcp() {
        return icp;
    }

    /**
     * 设置备案号
     *
     * @param icp 备案号
     */
    public void setIcp(String icp) {
        this.icp = icp;
    }

    /**
     * 获取底部版权信息
     *
     * @return copyright - 底部版权信息
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 设置底部版权信息
     *
     * @param copyright 底部版权信息
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取关键词
     *
     * @return keywords - 关键词
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键词
     *
     * @param keywords 关键词
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取网站名称
     *
     * @return title - 网站名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置网站名称
     *
     * @param title 网站名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取菜单管理使用说明
     *
     * @return menu_info - 菜单管理使用说明
     */
    public String getMenuInfo() {
        return menuInfo;
    }

    /**
     * 设置菜单管理使用说明
     *
     * @param menuInfo 菜单管理使用说明
     */
    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo;
    }

    /**
     * 获取角色管理使用说明
     *
     * @return role_info - 角色管理使用说明
     */
    public String getRoleInfo() {
        return roleInfo;
    }

    /**
     * 设置角色管理使用说明
     *
     * @param roleInfo 角色管理使用说明
     */
    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    /**
     * 获取组织机构管理使用说明
     *
     * @return dept_info - 组织机构管理使用说明
     */
    public String getDeptInfo() {
        return deptInfo;
    }

    /**
     * 设置组织机构管理使用说明
     *
     * @param deptInfo 组织机构管理使用说明
     */
    public void setDeptInfo(String deptInfo) {
        this.deptInfo = deptInfo;
    }

    /**
     * 获取用户管理使用说明
     *
     * @return user_info - 用户管理使用说明
     */
    public String getUserInfo() {
        return userInfo;
    }

    /**
     * 设置用户管理使用说明
     *
     * @param userInfo 用户管理使用说明
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}