package cn.javayuan.app.utils;

import cn.javayuan.app.entity.AppUser;

import javax.servlet.http.HttpServletRequest;

/**
 * app工具
 * Created by 李明元 on 2017/3/21.
 */
public class AppUtil {
    /**
     * 获取session中的appuser
     * @param request
     * @return
     */
    public  static AppUser getSessionAppUser(HttpServletRequest request){
        return  (AppUser) request.getSession().getAttribute("nowAppUser");
    }

    public  static String getSessionAppUserLocation(HttpServletRequest request){
        return  (String) request.getSession().getAttribute("nowAppUserLocation");

    }

    public static int getSessionUserId(HttpServletRequest request){
        return ((AppUser) request.getSession().getAttribute("nowAppUser")).getId();
    }

    private static final double EARTH_RADIUS = 6378137;
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     * @param userMap
     * @param findMap
     * @return
     */
    public static long GetDistance(String userMap, String findMap){
        double lng1=Double.parseDouble(userMap.split(",")[0]);
        double lat1=Double.parseDouble(userMap.split(",")[1]);
        double lng2=Double.parseDouble(findMap.split(",")[0]);
        double lat2=Double.parseDouble(findMap.split(",")[1]);
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return  Math.round(s);
    }

}
