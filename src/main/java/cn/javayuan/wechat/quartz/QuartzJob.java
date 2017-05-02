package cn.javayuan.wechat.quartz;

import cn.javayuan.app.entity.ApiConfig;
import cn.javayuan.app.service.LearnApiService;
import cn.javayuan.wechat.utils.GlobalConstants;
import cn.javayuan.wechat.utils.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 任务执行
 * Created by 李明元 on 2016/12/2.
 */
public class QuartzJob{

    @Autowired
    private LearnApiService apiService;
    /**
     * @Description: 任务执行获取token
     * @param
     * @author dapengniao
     * @date 2016年3月10日 下午4:34:26
     */
    public void workForToken() {
        try {
            getToken_getTicket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  任务执行体
     * @throws Exception
     */
    private void getToken_getTicket() throws Exception {
        if(GlobalConstants.interfaceUrlProperties==null){
            GlobalConstants.interfaceUrlProperties = new Properties();
        }
        List<ApiConfig> apiConfigs = apiService.selectAll();
        for(ApiConfig apiConfig :apiConfigs){
            GlobalConstants.interfaceUrlProperties.put(apiConfig.getApiKey(), apiConfig.getApiValue());
        }
        Map<String, String> params = new HashMap<String, String>();
        //获取token执行体
        params.put("grant_type", "client_credential");
        params.put("appid", GlobalConstants.getInterfaceUrl("appId"));
        params.put("secret", GlobalConstants.getInterfaceUrl("appSecret"));
        String jstoken = HttpUtils.sendGet(
                GlobalConstants.getInterfaceUrl("tokenUrl"), params);
        String accessToken = JSONObject.fromObject(jstoken).getString(
                "access_token"); // 获取到token并赋值保存
        GlobalConstants.interfaceUrlProperties.put("accessToken", accessToken);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"---token："+accessToken);
    }



}