package cn.javayuan.wechat.message.resp;

/**
 * 文本消息消息体
 * Created by 李明元 on 2016/12/2.
 */
public class TextMessage extends BaseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public TextMessage(String content) {
        Content = content;
    }

    public TextMessage() {
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "Content='" + Content + '\'' +
                '}';
    }
}