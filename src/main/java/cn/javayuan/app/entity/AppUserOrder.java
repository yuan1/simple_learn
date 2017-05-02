package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "app_user_order")
public class AppUserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "find_id")
    private Integer findId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "creat_time")
    private String creatTime;

    @Column(name = "find_user_id")
    private Integer findUserId;

    @Column(name = "order_id")
    private String orderId;

    /**
     * 0->预约 1->对方确认 2->对方取消 3->已完成
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
     * @return find_id
     */
    public Integer getFindId() {
        return findId;
    }

    /**
     * @param findId
     */
    public void setFindId(Integer findId) {
        this.findId = findId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return creat_time
     */
    public String getCreatTime() {
        return creatTime;
    }

    /**
     * @param creatTime
     */
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * @return find_user_id
     */
    public Integer getFindUserId() {
        return findUserId;
    }

    /**
     * @param findUserId
     */
    public void setFindUserId(Integer findUserId) {
        this.findUserId = findUserId;
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取0->预约 1->对方确认 2->对方取消 3->已完成
     *
     * @return state - 0->预约 1->对方确认 2->对方取消 3->已完成
     */
    public String getState() {
        return state;
    }

    /**
     * 设置0->预约 1->对方确认 2->对方取消 3->已完成
     *
     * @param state 0->预约 1->对方确认 2->对方取消 3->已完成
     */
    public void setState(String state) {
        this.state = state;
    }
}