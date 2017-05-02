package cn.javayuan.system.entity;

import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号
     */
    private String usercode;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 账号是否锁定，1：锁定，0未锁定
     */
    private Integer locked;

    private Integer sort;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证号码
     */
    private String idcard;

    private String tel;

    /**
     * 备注
     */
    private String remark;

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
     * 获取账号
     *
     * @return usercode - 账号
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * 设置账号
     *
     * @param usercode 账号
     */
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    /**
     * 获取姓名
     *
     * @return username - 姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置姓名
     *
     * @param username 姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取盐
     *
     * @return salt - 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取账号是否锁定，1：锁定，0未锁定
     *
     * @return locked - 账号是否锁定，1：锁定，0未锁定
     */
    public Integer getLocked() {
        return locked;
    }

    /**
     * 设置账号是否锁定，1：锁定，0未锁定
     *
     * @param locked 账号是否锁定，1：锁定，0未锁定
     */
    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取身份证号码
     *
     * @return idcard - 身份证号码
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证号码
     *
     * @param idcard 身份证号码
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}