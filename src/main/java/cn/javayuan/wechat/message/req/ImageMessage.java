package cn.javayuan.wechat.message.req;

/**
 * 图片消息
 * Created by 李明元 on 2016/12/2.
 */
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
