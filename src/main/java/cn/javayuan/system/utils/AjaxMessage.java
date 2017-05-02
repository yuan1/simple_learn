package cn.javayuan.system.utils;

/**
 * ajax消息工具
 * Created by 李明元 on 2016/11/23.
 */
public class AjaxMessage {
    private String message; //消息
    private int icon;   //弹出layer的图标 1是成功 2是失败


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public AjaxMessage(int icon,String message) {
        this.message = message;
        this.icon = icon;
    }

    public AjaxMessage() {
    }
}
