package cn.javayuan.ucpass.utils;

import cn.javayuan.ucpass.client.JsonReqClient;
import cn.javayuan.ucpass.utils.DateUtil;
import cn.javayuan.wechat.utils.GlobalConstants;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

/**
 * 云之讯 请求发送
 * Created by 李明元 on 2017/3/19.
 */
public class UCPASSUtil {


    /**
     * 发送验证码
     * @param to
     * @param param
     * @return
     */
    public static String sendSMS(String to, String param){
        String accountSid= GlobalConstants.getInterfaceUrl("ucAccountSid");
        String authToken=GlobalConstants.getInterfaceUrl("ucAuthToken");
        String appId=GlobalConstants.getInterfaceUrl("ucAppId");
        String templateId=GlobalConstants.getInterfaceUrl("ucTemplateId");
        String result=new JsonReqClient().templateSMS(accountSid, authToken, appId, templateId, to, param);
        System.out.println(result);
        String resp=JSONObject.fromObject(result).getString("resp");
        String respCode=JSONObject.fromObject(resp).getString("respCode");
        String createDate = null;
        if(respCode.equals("000000")){
            String templateSMS=JSONObject.fromObject(resp).getString("templateSMS");
            createDate=JSONObject.fromObject(templateSMS).getString("createDate");
        }
        return createDate;
    }

    /**
     * 生成6位数字
     * @return
     */
    public static String random() {
        int count = 6;
        char start = '0';
        char end = '9';
        Random rnd = new Random();
        char[] result = new char[count];
        int len = end - start + 1;
        while (count-- > 0) {
            result[count] = (char) (rnd.nextInt(len) + start);
        }
        return new String(result);
    }

    /**
     * 检查验证码
     * @param request
     * @param message
     * @return
     */
    public static String checkMessage(HttpServletRequest request,long message){
        String messageTime= (String) request.getSession().getAttribute("messageTime");
        String messageContent= (String) request.getSession().getAttribute("messageContent");
        long timeNow= Long.parseLong(DateUtil.dateToStr(new Date(),5));
        long timeUse= Long.parseLong(messageTime);
        long msgUse=Long.parseLong(messageContent);
        if(timeNow-timeUse>300){
            return "验证码已过期！";
        }
        if(message!=msgUse){
            return "验证码错误！";
        }
        return null;
    }
}
