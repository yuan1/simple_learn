package cn.javayuan.app.controller;

import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.service.AppUserValueService;
import cn.javayuan.app.service.LearnUserService;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.app.utils.TaobaoBCClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 私信
 * Created by 李明元 on 2017/3/26.
 */
@Controller
@RequestMapping("app/chat")
public class AppChatController {
    @Autowired
    private LearnUserService userService;

    @Autowired
    private AppUserValueService valueService;
    @GetMapping("index")
    public String index(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("chatList",valueService.selectChatList(request));
        return "app/chat/index";
    }

    @GetMapping("message/{id}")
    public String message(@PathVariable int id,Model model,HttpServletRequest request){
        AppUser fromUser=AppUtil.getSessionAppUser(request);
        AppUser toUser=userService.selectByKey(id);
        model.addAttribute("fromUser", fromUser);
        model.addAttribute("toUser",toUser);
        model.addAttribute("appKey", TaobaoBCClientUtil.appKey);
        valueService.saveValue(toUser.getId().toString(),fromUser.getId(),"chatList");
        return "app/chat/message";
    }
}
