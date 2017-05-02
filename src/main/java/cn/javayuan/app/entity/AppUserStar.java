package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "app_user_star")
public class AppUserStar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "find_id")
    private Integer findId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "creat_time")
    private String creatTime;

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
}