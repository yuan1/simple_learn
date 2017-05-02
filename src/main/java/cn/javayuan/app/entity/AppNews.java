package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "app_news")
public class AppNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String brief;

    private String author;

    private String breviary;

    private String content;

    private String laud;

    private String state;

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return brief
     */
    public String getBrief() {
        return brief;
    }

    /**
     * @param brief
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return breviary
     */
    public String getBreviary() {
        return breviary;
    }

    /**
     * @param breviary
     */
    public void setBreviary(String breviary) {
        this.breviary = breviary;
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
     * @return laud
     */
    public String getLaud() {
        return laud;
    }

    /**
     * @param laud
     */
    public void setLaud(String laud) {
        this.laud = laud;
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