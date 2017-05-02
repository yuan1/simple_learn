package cn.javayuan.app.entity;

import javax.persistence.*;

@Table(name = "api_config")
public class ApiConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "api_name")
    private String apiName;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_value")
    private String apiValue;

    @Column(name = "api_type")
    private String apiType;

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
     * 获取名称
     *
     * @return api_name - 名称
     */
    public String getApiName() {
        return apiName;
    }

    /**
     * 设置名称
     *
     * @param apiName 名称
     */
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    /**
     * @return api_key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return api_value
     */
    public String getApiValue() {
        return apiValue;
    }

    /**
     * @param apiValue
     */
    public void setApiValue(String apiValue) {
        this.apiValue = apiValue;
    }

    /**
     * @return api_type
     */
    public String getApiType() {
        return apiType;
    }

    /**
     * @param apiType
     */
    public void setApiType(String apiType) {
        this.apiType = apiType;
    }
}