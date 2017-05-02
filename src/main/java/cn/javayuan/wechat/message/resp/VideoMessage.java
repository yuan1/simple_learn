package cn.javayuan.wechat.message.resp;

/**
 * 视频消息
 * Created by 李明元 on 2016/12/2.
 */
public class VideoMessage  extends BaseMessage{

    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }

}
