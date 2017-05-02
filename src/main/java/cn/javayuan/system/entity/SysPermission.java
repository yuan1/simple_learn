package cn.javayuan.system.entity;

import javax.persistence.*;

@Table(name = "sys_permission")
public class SysPermission {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型：0->menu,1->permission,
     */
    private Integer type;

    /**
     * 访问url地址
     */
    private String url;

    /**
     * 权限代码字符串
     */
    private String percode;

    /**
     * 父结点id,如果为0表示是顶级菜单
     */
    private Integer parentid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源类型：0->menu,1->permission,
     *
     * @return type - 资源类型：0->menu,1->permission,
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置资源类型：0->top menu,2-->menu,3,->permission,
     *
     * @param type 资源类型：0->menu,1->permission,
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取访问url地址
     *
     * @return url - 访问url地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置访问url地址
     *
     * @param url 访问url地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取权限代码字符串
     *
     * @return percode - 权限代码字符串
     */
    public String getPercode() {
        return percode;
    }

    /**
     * 设置权限代码字符串
     *
     * @param percode 权限代码字符串
     */
    public void setPercode(String percode) {
        this.percode = percode;
    }

    /**
     * 获取父结点id,如果为0表示是顶级菜单
     *
     * @return parentid - 父结点id,如果为0表示是顶级菜单
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 设置父结点id,如果为0表示是顶级菜单
     *
     * @param parentid 父结点id,如果为0表示是顶级菜单
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}