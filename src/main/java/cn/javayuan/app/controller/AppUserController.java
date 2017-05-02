package cn.javayuan.app.controller;

import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.service.*;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.app.utils.QiniuUtil;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * app用户
 * Created by 李明元 on 2017/3/17.
 */
@Controller
@RequestMapping("/app/user")
public class AppUserController {
    
    @Autowired
    private LearnUserService userService;

    @Autowired
    private AppUserValueService valueService;

    @Autowired
    private LearnFindService findService;

    @Autowired
    private AppUserOrderService orderService;

    @Autowired
    private AppUserEvaService evaService;
    /**
     * 我的信息
     * @return
     */
    @GetMapping("my")
    public String myInfo(){
        return "app/user/my";
    }

    /**
     * 获取我的信息
     * @param request
     * @return
     */
    @PostMapping("my/info")
    @ResponseBody
    public AppUser myInfoGet(HttpServletRequest request){
        AppUser appUser = AppUtil.getSessionAppUser(request);
        return userService.selectByKey(appUser.getId());
    }

    /**
     * 编辑我的信息
     * @param request
     * @param model
     * @return
     */
    @GetMapping("my/edit")
    public String myEdit(HttpServletRequest request, Model model){
        AppUser appUser = AppUtil.getSessionAppUser(request);
        model.addAttribute("myInfo" ,userService.selectByKey(appUser.getId()));
        return "app/user/edit";
    }

    /**
     * 上传照片
     * @param filedata
     * @return
     * @throws IOException
     */
    @PostMapping("upload/image")
    @ResponseBody
    public AjaxMessage uploadImage( @RequestParam(value = "file", required = false) MultipartFile filedata) throws IOException {
        AjaxMessage message = new AjaxMessage();
        if (filedata != null && !filedata.isEmpty()) {
            String image= QiniuUtil.uploadToQiniu(filedata.getBytes());
            message.setIcon(1);
            message.setMessage(image);
            return message;
        }
        message.setIcon(2);
        message.setMessage("上传失败！");
        return message;
    }

    /**
     * 执行编辑我的信息
     * @param user
     * @return
     */
    @PostMapping("my/edit/do")
    @ResponseBody
    public AjaxMessage myEditDo(AppUser user){
        if(userService.updateAppUserLess(user)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    /**
     * 跳转预约
     * @param model
     * @return
     */
    @GetMapping("find")
    public String myFind(Model model){
        model.addAttribute("type","1");
        model.addAttribute("action","find");
        return "app/user/find";
    }

    /**
     * 预约列表
     * @param type
     * @param action
     * @param request
     * @return
     */
    @RequestMapping("find/list")
    @ResponseBody
    public List<Map<String, Object>> listContent(String type,String action, HttpServletRequest request) {
        return findService.selectUserFindByType(request,type,action);
    }

    /**
     * 收藏
     * @param model
     * @return
     */
    @GetMapping("star")
    public String myStar(Model model){
        model.addAttribute("type","1");
        model.addAttribute("action","star");
        return "app/user/find";
    }

    /**
     * 我的预约列表
     * @param model
     * @param request
     * @return
     */
    @GetMapping("my/order")
    public String myOrder(Model model,HttpServletRequest request){
        model.addAttribute("action","myOrder");
        model.addAttribute("orderInfo",orderService.selectOrderByUserId(AppUtil.getSessionUserId(request)));
        return "app/user/order";
    }

    /**
     * 预约我的列表
     * @param model
     * @param request
     * @return
     */
    @GetMapping("order/my")
    public String orderMy(Model model,HttpServletRequest request){
        model.addAttribute("action","orderMy");
        model.addAttribute("orderInfo",orderService.selectOrderMyByUserId(AppUtil.getSessionUserId(request)));
        return "app/user/order";
    }

    /**
     * 更改预约状态
     * @param id
     * @param state
     * @return
     */
    @PostMapping("order/state")
    @ResponseBody
    public AjaxMessage orderState(int id,String state){
        AjaxMessage message = new AjaxMessage();
        if(orderService.updateOrderState(id,state)){
            message.setIcon(1);
            return message;
        }
        message.setIcon(2);
        return message;
    }

    /**
     * 预约我的
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("order/my/{orderId}")
    public String orderMyGet(@PathVariable int orderId,Model model){
        model.addAttribute("order",orderService.selectOrder(orderId));
        model.addAttribute("action","orderMy");
        return "app/user/order_show";
    }

    /**
     * 我的预约
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("my/order/{orderId}")
    public String myOrderGet(@PathVariable int orderId,Model model){
        model.addAttribute("order",orderService.selectOrder(orderId));
        model.addAttribute("action","myOrder");
        return "app/user/order_show";
    }

    /**
     * 发表评价
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("eva/{orderId}")
    public String evaOrder(@PathVariable int orderId,Model model){
        if(evaService.checkEva(orderId)){
            model.addAttribute("info","您已经评价！");
            return "app/common/_info";
        }
        model.addAttribute("orderId",orderId);
        return "app/user/evaluate";
    }

    /**
     * 发表评价操作
     * @param content
     * @param type
     * @return
     */
    @PostMapping("eva/do")
    @ResponseBody
    public AjaxMessage evaAddDo(String content,String type,int orderId,HttpServletRequest request){
        AjaxMessage message=new AjaxMessage();
        if(evaService.checkEva(orderId)){
            message.setIcon(2);
            message.setMessage("您已经评价！");
            return message;
        }
        if(evaService.saveEva(content,type,orderId,request)){
            message.setIcon(1);
            message.setMessage("评价成功！");
            return message;
        }
        message.setIcon(2);
        message.setMessage("评价失败！");
        return message;
    }

    /**
     * 反馈页面
     * @return
     */
    @GetMapping("feedback")
    public String feedback(){
        return "app/user/feedback";
    }

    /**
     * 保存反馈
     * @param feedback
     * @param request
     * @return
     */
    @PostMapping("feedback/do")
    @ResponseBody
    public AjaxMessage feedbackDo(String feedback,HttpServletRequest request){
        AjaxMessage message=new AjaxMessage(1,"反馈成功，感谢您的反馈！");
        if(!valueService.saveFeedback(request,feedback)){
            message.setIcon(2);
            message.setMessage("反馈失败！");
        }
        return message;
    }

}
