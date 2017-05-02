package cn.javayuan.app.utils;

import cn.javayuan.wechat.utils.GlobalConstants;
import cn.javayuan.wechat.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * 图灵机器人工具
 * Created by 李明元 on 2017/4/4.
 */
public class TuLingUtil {
    /* 图灵机器人返回数据类型状态码(官方固定) */
    /**
     * 文本
     */
    private static final Integer TEXT_CODE = 100000;
    /**
     * 列车
     */
    public static final Integer TRAIN_CODE = 305000;
    /**
     * 航班
     */
    private static final Integer FLIGHT_CODE = 306000;
    /**
     * 链接类
     */
    private static final Integer LINK_CODE = 200000;
    /**
     * 新闻
     */
    private static final Integer NEWS_CODE = 302000;
    /**
     * 菜谱、视频、小说
     */
    private static final Integer MENU_CODE = 308000;
    /**
     * key的长度错误（32位）
     */
    private static final Integer LENGTH_WRONG_CODE = 40001;
    /**
     * 请求内容为空
     */
    private static final Integer EMPTY_CONTENT_CODE = 40002;
    /**
     * key错误或帐号未激活
     */
    private static final Integer KEY_WRONG_CODE = 40003;
    /**
     * 当天请求次数已用完
     */
    private static final Integer NUMBER_DONE_CODE = 40004;
    /**
     * 暂不支持该功能
     */
    private static final Integer NOT_SUPPORT_CODE = 40005;
    /**
     * 服务器升级中
     */
    private static final Integer UPGRADE_CODE = 40006;
    /**
     * 服务器数据格式异常
     */
    private static final Integer DATA_EXCEPTION_CODE = 40007;


    /**
     * 图灵机器人接口地址 http://www.tuling123.com/openapi/api
     */
    private final static String TURING_API_URL = GlobalConstants.getInterfaceUrl("tlApiUrl");
    /**
     * 图灵机器人key
     */
    private final static String TURING_API_KEY =GlobalConstants.getInterfaceUrl("tlApiKey");
    /**
     * 处理图灵机器人返回的结果
     */
    public static String processTuRingResult(String content,String openid) throws Exception {
        Map<String, String> params = new HashedMap();
        params.put("key",TURING_API_KEY);
        params.put("info",content);
        params.put("userid",openid);
        String result = HttpUtils.sendGet(TURING_API_URL,params);
        JSONObject rootObj = JSON.parseObject(result);
        int code = rootObj.getIntValue("code");
        if (TEXT_CODE.equals(code)) {
            return rootObj.getString("text");
        } else if (LINK_CODE.equals(code)) {
            return"<a href='" + rootObj.getString("url") + "'>"
                    + rootObj.getString("text") + "</a>";
        } else if (LENGTH_WRONG_CODE.equals(code) || KEY_WRONG_CODE.equals(code)) {
            return "我现在想一个人静一静,请等下再跟我聊天";
        } else if (EMPTY_CONTENT_CODE.equals(code)) {
            return  "你不说话,我也没什么好说的";
        } else if (NUMBER_DONE_CODE.equals(code)) {
            return "我今天有点累了,明天再找我聊吧！";
        } else if (NOT_SUPPORT_CODE.equals(code)) {
            return"这个我还没学会,我以后会慢慢学的";
        } else if (UPGRADE_CODE.equals(code)) {
            return"我经验值满了,正在升级中...";
        } else if (DATA_EXCEPTION_CODE.equals(code)) {
            return "我怎么话都说不清楚了";
        }
        return null;
    }
}
