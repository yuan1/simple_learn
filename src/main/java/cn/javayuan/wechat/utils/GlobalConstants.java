package cn.javayuan.wechat.utils;

import java.util.Properties;

/**
 * 配置文件全局变量
 * Created by 李明元 on 2016/12/2.
 */

public class GlobalConstants {

    public static Properties interfaceUrlProperties;

    /**
     * @param key
     * @return
     */
    public static String getInterfaceUrl(String key) {
        return (String) interfaceUrlProperties.get(key);
    }



}
