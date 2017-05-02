package cn.javayuan.app.service.impl;

import cn.javayuan.app.entity.ApiConfig;
import cn.javayuan.app.service.LearnApiService;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import cn.javayuan.wechat.utils.GlobalConstants;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * api接口
 * Created by 李明元 on 2017/3/19.
 */
@Service
public class LearnApiServiceImpl extends BaseServiceImpl<ApiConfig> implements LearnApiService{
    /**
     * 查询接口配置
     * @return
     */
    @Override
    public List<ApiConfig> selectApiByType(String apiType) {
        Example example= new Example(ApiConfig.class);
        example.createCriteria().andEqualTo("apiType",apiType);
        return selectByExample(example);
    }
    /**
     * 更新全部api
     * @param apiConfigs
     * @return
     */
    @Override
    public boolean updateApiList(List<ApiConfig> apiConfigs) {
        boolean bl =false;
        for(ApiConfig apiConfig:apiConfigs){
            if(updateAll(apiConfig)){
                bl=true;
            }
        }
        if(bl){
            updateGlobalConstants();
        }
        return bl;
    }

    /**
     * 更新全局变量
     */
    private void updateGlobalConstants(){
        List<ApiConfig> apiConfigs = selectAll();
        for(ApiConfig apiConfig :apiConfigs){
            GlobalConstants.interfaceUrlProperties.put(apiConfig.getApiKey(), apiConfig.getApiValue());
        }
    }
    /**
     * 通过apikey查询
     * @param apiKey
     * @return
     */
    @Override
    public String selectApiByApiKey(String apiKey) {
        Example example = new Example(ApiConfig.class);
        example.createCriteria().andEqualTo("apiKey",apiKey);
        return selectByExample(example).get(0).getApiValue();
    }
}
