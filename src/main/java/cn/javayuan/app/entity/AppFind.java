package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "app_find")
public class AppFind {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 编号
     */
    @Column(name = "find_id")
    private String findId;

    /**
     * 名称
     */
    private String name;

    /**
     * 发布地点名称
     */
    @Column(name = "location_name")
    private String locationName;

    @Column(name = "location_map")
    private String locationMap;

    /**
     * 类型
     */
    private String type;

    /**
     * 地点名称
     */
    @Column(name = "address_name")
    private String addressName;

    @Column(name = "address_map")
    private String addressMap;

    /**
     * 内容
     */
    private String content;

    @Column(name = "find_time")
    private String findTime;

    /**
     * 注意事项
     */
    private String attention;

    /**
     * 状态 0->未发布 1—>正常 2->禁止 3->已删除
     */
    private String state;

    /**
     * 距离
     */
    private Integer gap;

    /**
     * appid
     */
    @Column(name = "app_user_id")
    private Integer appUserId;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取编号
     *
     * @return find_id - 编号
     */
    public String getFindId() {
        return findId;
    }

    /**
     * 设置编号
     *
     * @param findId 编号
     */
    public void setFindId(String findId) {
        this.findId = findId;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取发布地点名称
     *
     * @return location_name - 发布地点名称
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * 设置发布地点名称
     *
     * @param locationName 发布地点名称
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return location_map
     */
    public String getLocationMap() {
        return locationMap;
    }

    /**
     * @param locationMap
     */
    public void setLocationMap(String locationMap) {
        this.locationMap = locationMap;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取地点名称
     *
     * @return address_name - 地点名称
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 设置地点名称
     *
     * @param addressName 地点名称
     */
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    /**
     * @return address_map
     */
    public String getAddressMap() {
        return addressMap;
    }

    /**
     * @param addressMap
     */
    public void setAddressMap(String addressMap) {
        this.addressMap = addressMap;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return find_time
     */
    public String getFindTime() {
        return findTime;
    }

    /**
     * @param findTime
     */
    public void setFindTime(String findTime) {
        this.findTime = findTime;
    }

    /**
     * 获取注意事项
     *
     * @return attention - 注意事项
     */
    public String getAttention() {
        return attention;
    }

    /**
     * 设置注意事项
     *
     * @param attention 注意事项
     */
    public void setAttention(String attention) {
        this.attention = attention;
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

    /**
     * 获取距离
     *
     * @return gap - 距离
     */
    public Integer getGap() {
        return gap;
    }

    /**
     * 设置距离
     *
     * @param gap 距离
     */
    public void setGap(Integer gap) {
        this.gap = gap;
    }

    /**
     * 获取appid
     *
     * @return app_user_id - appid
     */
    public Integer getAppUserId() {
        return appUserId;
    }

    /**
     * 设置appid
     *
     * @param appUserId appid
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
}