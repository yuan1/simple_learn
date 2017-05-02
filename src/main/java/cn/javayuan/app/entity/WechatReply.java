package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "wechat_reply")
public class WechatReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关键字
     */
    private String name;

    /**
     * 回复
     */
    private String reply;

    /**
     * 生成时间
     */
    private String creat;

    /**
     * 状态
     */
    private String state;

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
     * 获取关键字
     *
     * @return name - 关键字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置关键字
     *
     * @param name 关键字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取回复
     *
     * @return reply - 回复
     */
    public String getReply() {
        return reply;
    }

    /**
     * 设置回复
     *
     * @param reply 回复
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * 获取生成时间
     *
     * @return creat - 生成时间
     */
    public String getCreat() {
        return creat;
    }

    /**
     * 设置生成时间
     *
     * @param creat 生成时间
     */
    public void setCreat(String creat) {
        this.creat = creat;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {
        this.state = state;
    }
}