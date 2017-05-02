package cn.javayuan.app.entity;

import java.util.List;

/**
 * 用于传输list的对象--apiconfig
 * Created by 李明元 on 2017/3/19.
 */
public class ApiConfigModel {
    private List<ApiConfig>  apis;

    public List<ApiConfig> getApis() {
        return apis;
    }

    public void setApis(List<ApiConfig> apis) {
        this.apis = apis;
    }

    public ApiConfigModel(List<ApiConfig> apis) {
        super();
        this.apis = apis;
    }

    public ApiConfigModel() {
        super();
    }
}
