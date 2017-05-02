package cn.javayuan.app.service;

import cn.javayuan.app.entity.ApiConfig;
import cn.javayuan.system.service.BaseService;

import java.util.List;

/**
 * api接口处理
 * Created by 李明元 on 2017/3/19.
 */

public interface LearnApiService extends BaseService<ApiConfig> {
    List<ApiConfig> selectApiByType(String apiType);
    boolean updateApiList(List<ApiConfig> apiConfigs);

    String selectApiByApiKey(String apiKey);

}
