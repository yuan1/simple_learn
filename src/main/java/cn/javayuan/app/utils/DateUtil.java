package cn.javayuan.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期计算
 * Created by 李明元 on 2017/4/7.
 */
public class DateUtil {
    /**
     * 两个string类型的日期比较大小
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 返回两个string类型日期之间相差的天数
     * @param smdate
     * @param bdate
     * @return
     */
    public static int daysBetween(String smdate,String bdate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try{
            cal.setTime(sdf.parse(smdate));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            time2 = cal.getTimeInMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 返回两个string类型日期相差的小时数
     * @param startTime
     * @param endTime
     * @return
     */
    public static int daysBetween2(String startTime, String endTime) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;
        try{
            cal.setTime(sdf.parse(startTime));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(endTime));
            time2 = cal.getTimeInMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600);

        return Integer.parseInt(String.valueOf(between_days));
    }
    /**
     * 计算两段日期的重合日期
     * @param str1 开始日期1
     * @param str2 结束日期1
     * @param str3 开始日期2
     * @param str4 结束日期2
     * @return
     * @throws Exception
     */
    public static Map<String,Object> comparisonRQ(String str1, String str2, String str3,
                                                  String str4) throws Exception {
        String mesg = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startdate = "";
        String enddate = "";
        try {
            Date dt1 = df.parse(str1);
            Date dt2 = df.parse(str2);
            Date dt3 = df.parse(str3);
            Date dt4 = df.parse(str4);
            if (dt1.getTime()<=dt3.getTime()&&dt3.getTime()<=dt2.getTime()&&dt2.getTime()<=dt4.getTime()) {
                mesg = "f";//重合
                startdate = str3;
                enddate = str2;
            }
            if (dt1.getTime()>=dt3.getTime()&&dt3.getTime()<=dt2.getTime()&&dt2.getTime()<=dt4.getTime()) {
                mesg = "f";//重合
                startdate = str1;
                enddate = str2;
            }

            if (dt3.getTime()<=dt1.getTime()&&dt1.getTime()<=dt4.getTime()&&dt4.getTime()<=dt2.getTime()) {
                mesg = "f";//重合
                startdate = str1;
                enddate = str4;
            }
            if (dt3.getTime()>=dt1.getTime()&&dt1.getTime()<=dt4.getTime()&&dt4.getTime()<=dt2.getTime()) {
                mesg = "f";//重合
                startdate = str3;
                enddate = str4;
            }

            System.out.println(startdate+"----"+enddate);


        }catch (ParseException e) {
            e.printStackTrace();
            throw new ParseException(e.getMessage(), 0);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        return map;
    }

    /**
     * 监测是否逾期
     * @param findTime
     * @return
     */
    public static String checkDate(String findTime){
        String createTime= cn.javayuan.ucpass.utils.DateUtil.dateToStr(new Date());
        String less;
        int day= daysBetween(createTime,findTime);
        if(day==0){
            int hour=daysBetween2(createTime,findTime);
            if(hour==0){
                less="已逾期";
            }else {
                less=hour+"小时";
            }
        }else if(day>0) {
            less=day+"天";
        }else {
            less="已逾期";
        }
        return less;
    }
}
