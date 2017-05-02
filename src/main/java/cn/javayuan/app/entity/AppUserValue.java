package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "app_user_value")
public class AppUserValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "app_user_id")
    private Integer appUserId;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "user_value")
    private String userValue;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return app_user_id
     */
    public Integer getAppUserId() {
        return appUserId;
    }

    /**
     * @param appUserId
     */
    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
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
     * @return user_value
     */
    public String getUserValue() {
        return userValue;
    }

    /**
     * @param userValue
     */
    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    @Override
    public String toString() {
        return "AppUserValue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appUserId=" + appUserId +
                ", createTime='" + createTime + '\'' +
                ", userValue='" + userValue + '\'' +
                '}';
    }
}