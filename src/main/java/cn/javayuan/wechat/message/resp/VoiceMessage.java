package cn.javayuan.wechat.message.resp;

/**
 * 语音消息
 * Created by 李明元 on 2016/12/2.
 */
public class VoiceMessage extends BaseMessage {

    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }

}