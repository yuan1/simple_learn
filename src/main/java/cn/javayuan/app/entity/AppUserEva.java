package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "app_user_eva")
public class AppUserEva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "find_id")
    private Integer findId;

    @Column(name = "userI_id")
    private Integer useriId;

    private String content;

    private String type;

    @Column(name = "create_time")
    private String createTime;

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
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * @return userI_id
     */
    public Integer getUseriId() {
        return useriId;
    }

    /**
     * @param useriId
     */
    public void setUseriId(Integer useriId) {
        this.useriId = useriId;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }
}