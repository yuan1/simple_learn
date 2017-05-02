package cn.javayuan.wechat.common;


import cn.javayuan.wechat.utils.GlobalConstants;
import cn.javayuan.wechat.utils.HttpUtils;
import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 *  获取微信用户信息
 * Created by 李明元 on 2016/12/2.
 */
public class GetUseInfo {

    /**
     * 通过openid获取用户微信信息
     * @param openid
     * @return
     * @throws Exception
     */
    public static HashMap<String, String> Openid_userinfo(String openid)
            throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token",
                GlobalConstants.getInterfaceUrl("accessToken"));  //定时器中获取到的token
        params.put("openid", openid);  //需要获取的用户的openid
        params.put("lang", "zh_CN");
        String subscribers = HttpUtils.sendGet(
                GlobalConstants.getInterfaceUrl("openidUserinfoUrl"), params);
        System.out.println(subscribers);
        params.clear();
        //这里返回参数只取了昵称、头像、和性别
        params.put("nickname",
                JSONObject.fromObject(subscribers).getString("nickname")); //昵称
        params.put("headimgurl",
                JSONObject.fromObject(subscribers).getString("headimgurl"));  //图像
        params.put("sex", JSONObject.fromObject(subscribers).getString("sex"));  //性别
        return params;
    }

}
