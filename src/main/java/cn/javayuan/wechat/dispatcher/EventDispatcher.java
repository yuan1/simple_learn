package cn.javayuan.wechat.dispatcher;

import cn.javayuan.wechat.common.GetUseInfo;
import cn.javayuan.wechat.message.resp.*;
import cn.javayuan.wechat.utils.HttpPostUploadUtil;
import cn.javayuan.wechat.utils.MessageUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * 事件消息业务分发器
 * Created by 李明元 on 2016/12/2.
 */
public class EventDispatcher {
    private static Logger logger = Logger.getLogger(EventDispatcher.class);

    public static String processEvent(Map<String, String> map) {
        String openid = map.get("FromUserName"); // 用户openid
        String mpid = map.get("ToUserName"); // 公众号原始ID
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
            try {
                //对图文消息
                NewsMessage newmsg = new NewsMessage();
                newmsg.setToUserName(openid);
                newmsg.setFromUserName(mpid);
                newmsg.setCreateTime(new Date().getTime());
                newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                HashMap<String, String> userinfo = GetUseInfo.Openid_userinfo(openid);
                Article article = new Article();
                article.setDescription("欢迎关注约学约行微信客户端！"); //图文消息的描述
                article.setPicUrl(userinfo.get("headimgurl")); //图文消息图片地址
                article.setTitle("尊敬的：" + userinfo.get("nickname") + ",您好，点击绑定微信");  //图文消息标题
                article.setUrl("http://yxyx.javayuan.cn/app/wechat/login/"+openid);  //图文url链接
                List<Article> list = new ArrayList<Article>();
                list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                newmsg.setArticleCount(list.size());
                newmsg.setArticles(list);
                return MessageUtil.newsMessageToXml(newmsg);
            } catch (Exception e) {
                logger.error(e, e);
            }

        }
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            System.out.println("==============这是取消关注事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
            System.out.println("==============这是扫描二维码事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
            System.out.println("==============这是位置上报事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            switch (map.get("EventKey")) {
                case "wechat_reg": {
                    TextMessage textMessage = new TextMessage();
                    textMessage.setFromUserName(mpid);
                    textMessage.setToUserName(openid);
                    textMessage.setContent("没有账号？马上注册<a href='http://yxyx.javayuan.cn/app/wechat/regist/"+openid+"'>点击注册</a>" +
                            "已有账号？<a href='http://yxyx.javayuan.cn/app/wechat/login/"+openid+"'>点击绑定</a>");
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                    return MessageUtil.textMessageToXml(textMessage);
                }
                case "wechat_index":{
                    TextMessage textMessage = new TextMessage();
                    textMessage.setFromUserName(mpid);
                    textMessage.setToUserName(openid);
                    textMessage.setContent("我的首页<a href='http://yxyx.javayuan.cn/app/wechat/index/"+openid+"'>点击进入</a>");
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                    return MessageUtil.textMessageToXml(textMessage);
                }
                case "wechat_about":{
                    TextMessage textMessage = new TextMessage();
                    textMessage.setFromUserName(mpid);
                    textMessage.setToUserName(openid);
                    textMessage.setContent("约学约行<a href='http://javayuan.cn'>点击进入作者主页</a>");
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                    return MessageUtil.textMessageToXml(textMessage);
                }
            }
            System.out.print(map.get("EventKey"));
            System.out.println("==============这是自定义菜单点击事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单 View 事件
            System.out.println("==============这是自定义菜单 View 事件！");
        }

        return null;
    }
}