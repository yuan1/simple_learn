package cn.javayuan.system.utils;

/**
 * Ajax返回工具
 * Created by 李明元 on 2016/11/23.
 */
public class AjaxMessageUtil {
    public static AjaxMessage updateSuccess(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(1);
        ajaxMessage.setMessage("修改成功！");
        return  ajaxMessage;
    }
    public static  AjaxMessage updateError(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(2);
        ajaxMessage.setMessage("修改失败！");
        return  ajaxMessage;
    }
    public static AjaxMessage addSuccess(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(1);
        ajaxMessage.setMessage("添加成功！");
        return  ajaxMessage;
    }
    public static  AjaxMessage addError(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(2);
        ajaxMessage.setMessage("添加失败！");
        return  ajaxMessage;
    }
    public static AjaxMessage delSuccess(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(1);
        ajaxMessage.setMessage("删除成功！");
        return  ajaxMessage;
    }
    public static  AjaxMessage delError(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(2);
        ajaxMessage.setMessage("删除失败！");
        return  ajaxMessage;
    }

    public static AjaxMessage startSuccess(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(1);
        ajaxMessage.setMessage("启用成功！");
        return  ajaxMessage;
    }
    public static AjaxMessage stopSuccess(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(1);
        ajaxMessage.setMessage("停用成功！");
        return  ajaxMessage;
    }
    public static AjaxMessage startError(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(2);
        ajaxMessage.setMessage("启用失败！");
        return  ajaxMessage;
    }
    public static AjaxMessage stopError(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(2);
        ajaxMessage.setMessage("停用失败！");
        return  ajaxMessage;
    }
    public static AjaxMessage usernameError(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(2);
        ajaxMessage.setMessage("用户名已存在！");
        return  ajaxMessage;
    }
    public static AjaxMessage idcardError(){
        AjaxMessage ajaxMessage = new AjaxMessage();
        ajaxMessage.setIcon(2);
        ajaxMessage.setMessage("身份证号码已存在！");
        return  ajaxMessage;
    }
}
