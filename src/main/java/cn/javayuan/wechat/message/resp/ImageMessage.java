package cn.javayuan.wechat.message.resp;

/**
 * 图片消息
 * Created by 李明元 on 2016/12/2.
 */
public class ImageMessage extends BaseMessage{
    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
