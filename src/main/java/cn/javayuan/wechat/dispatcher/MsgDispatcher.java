package cn.javayuan.wechat.dispatcher;

import cn.javayuan.app.service.WechatReplyService;
import cn.javayuan.app.service.impl.WechatReplyServiceImpl;
import cn.javayuan.app.utils.TuLingUtil;
import cn.javayuan.wechat.message.resp.Article;
import cn.javayuan.wechat.message.resp.NewsMessage;
import cn.javayuan.wechat.message.resp.TextMessage;
import cn.javayuan.wechat.utils.Code;
import cn.javayuan.wechat.utils.HttpUtils;
import cn.javayuan.wechat.utils.MessageUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息业务分发器
 * Created by 李明元 on 2016/12/2.
 */
public class MsgDispatcher {

    public static String processMessage(Map<String, String> map) throws Exception {
        String openid = map.get("FromUserName"); //用户 openid
        String mpid = map.get("ToUserName");   //公众号原始 ID\
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            // 普通文本消息
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            String content=map.get("Content");
            WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            WechatReplyService replyService = (WechatReplyService) context.getBean("wechatReplyServiceImpl");
            String reply= replyService.selectReplyByName(content);
            if(reply!=null&&!reply.equals("")){
                txtmsg.setContent(reply);
            }else{
                txtmsg.setContent(TuLingUtil.processTuRingResult(content,openid));
            }
            return MessageUtil.textMessageToXml(txtmsg);
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");
        }

        //这个是错误代码回复
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            System.out.println("==============这是视频消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
        }

        return null;
    }
}