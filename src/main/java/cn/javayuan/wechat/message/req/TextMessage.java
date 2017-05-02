package cn.javayuan.wechat.message.req;

/**
 *  文本消息
 * Created by 李明元 on 2016/12/2.
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}