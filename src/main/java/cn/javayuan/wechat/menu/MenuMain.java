package cn.javayuan.wechat.menu;

import cn.javayuan.wechat.utils.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 菜单生成
 * Created by 李明元 on 2016/12/2.
 */
public class MenuMain {
    public static void main(String[] args) {
        ClickButton clickButton = new ClickButton();
        clickButton.setName("首页");
        clickButton.setType("click");
        clickButton.setKey("wechat_index");

        ClickButton clickButton2 = new ClickButton();
        clickButton2.setName("注册");
        clickButton2.setType("click");
        clickButton2.setKey("wechat_reg");

        ClickButton clickButton3 = new ClickButton();
        clickButton3.setName("关于");
        clickButton3.setType("click");
        clickButton3.setKey("wechat_about");

//        ViewButton vbt=new ViewButton();
//        vbt.setUrl("http://www.javayuan.cn");
//        vbt.setName("接口测试");
//        vbt.setType("view");
//
//        ViewButton vbt2=new ViewButton();
//        vbt2.setUrl("http://wx.javayuan.cn/wechat/location/index");
//        vbt2.setName("位置测试");
//        vbt2.setType("view");

        JSONArray sub_button=new JSONArray();
        sub_button.add(clickButton2);
        sub_button.add(clickButton);
        sub_button.add(clickButton3);

//        JSONObject buttonOne=new JSONObject();
//        buttonOne.put("name", "菜单");
//        buttonOne.put("sub_button", sub_button);
//
//        JSONArray button=new JSONArray();
//        button.add(vbt);
//        button.add(vbt2);
//        button.add(buttonOne);

        JSONObject menujson=new JSONObject();
        menujson.put("button", sub_button);
        System.out.println(menujson);
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+"Nyn5uJJzzrtpb-2rAjxwTprav5m3KEEViOqDMyAGQexw8BeYIHqzb5Zq4_9z8hPX3PnKnuDlxMT3ghwd2NT95EX8I3bHpuWH7uNpvuGoQTvvJBnpzeLgxL37Ljt0Ck86BBDdAFALLN";

        try{
            String rs= HttpUtils.sendPostBuffer(url, menujson.toString());
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }

    }
}
