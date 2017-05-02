package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 头像
     */
    private String image;

    /**
     * 微信id
     */
    private String openid;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 类型（微信？app）
     */
    private String type;

    /**
     * 创建时间
     */
    private String creat;

    private String token;

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
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
     * 获取头像
     *
     * @return image - 头像
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置头像
     *
     * @param image 头像
     */
    public void setImage(String image) {
        this.image = image;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取类型（微信？app）
     *
     * @return type - 类型（微信？app）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型（微信？app）
     *
     * @param type 类型（微信？app）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return creat - 创建时间
     */
    public String getCreat() {
        return creat;
    }

    /**
     * 设置创建时间
     *
     * @param creat 创建时间
     */
    public void setCreat(String creat) {
        this.creat = creat;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", image='" + image + '\'' +
                ", openid='" + openid + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", creat='" + creat + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}