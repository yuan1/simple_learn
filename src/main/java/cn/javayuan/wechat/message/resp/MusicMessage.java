package cn.javayuan.wechat.message.resp;

/**
 * 音乐消息
 * Created by 李明元 on 2016/12/2.
 */
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}