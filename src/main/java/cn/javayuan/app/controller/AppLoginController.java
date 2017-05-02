package cn.javayuan.app.controller;

import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.service.LearnUserService;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.ucpass.utils.UCPASSUtil;
import cn.javayuan.system.utils.AjaxMessage;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * web app 端登录
 * Created by 李明元 on 2017/3/15.
 */
@Controller
@RequestMapping("/app")
public class AppLoginController {
    @Autowired
    private LearnUserService userService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "app/login";
    }

    /**
     * 跳转主页
     *
     * @return
     */
    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("openid","0");
        model.addAttribute("type","1");
        return "app/index";
    }

    /**
     * 通过token登录
     *
     * @param token
     * @return
     */
    @PostMapping("login/token")
    @ResponseBody
    public AjaxMessage loginByToken(String token, HttpServletRequest request) {
        AjaxMessage message = new AjaxMessage();
        AppUser user = userService.selectByToken(token);
        if (user != null) {
            request.getSession().setAttribute("nowAppUser", user);
            message.setIcon(1);
        } else {
            message.setIcon(2);
            message.setMessage("密码失效！");
        }
        return message;
    }
    /**
     * 通过openid登录
     *
     * @param openid
     * @return
     */
    @PostMapping("login/openid")
    @ResponseBody
    public AjaxMessage loginByopenId(String openid, HttpServletRequest request) {
        AjaxMessage message = new AjaxMessage();
        AppUser user = userService.selectByOpenId(openid);
        if (user != null) {
            request.getSession().setAttribute("nowAppUser", user);
            message.setIcon(1);
        } else {
            message.setIcon(2);
            message.setMessage("密码失效！");
        }
        return message;
    }

    /**
     * 登录操作
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login/do")
    @ResponseBody
    public AjaxMessage loginDo(HttpServletRequest request, String username, String password) {
        AjaxMessage message = new AjaxMessage();
        AppUser user = userService.selectByUserNamePassword(username, password);
        if (user != null) {
            request.getSession().setAttribute("nowAppUser", user);
            message.setMessage(user.getToken());
            message.setIcon(1);
        } else {
            message.setIcon(2);
            message.setMessage("登录失败！");
        }
        return message;
    }

    /**
     * 发送验证码
     *
     * @param request
     * @param mobile
     * @return
     */
    @PostMapping("message")
    @ResponseBody
    public AjaxMessage sendMessage(HttpServletRequest request, String mobile) {
        AjaxMessage message = new AjaxMessage();
        String param = UCPASSUtil.random();
        String time = UCPASSUtil.sendSMS(mobile, param);
        if (time != null) {
            request.getSession().setAttribute("messageTime", time);
            request.getSession().setAttribute("messageContent", param);
            request.getSession().setAttribute("mobileReg", mobile);
            message.setIcon(1);
            message.setMessage("发送成功！");
            request.getSession().setAttribute("mobileSend", "limingyuan");
        } else {
            message.setIcon(2);
            message.setMessage("失败！");
        }
        return message;
    }

    /**
     * 检查验证码
     *
     * @param request
     * @param message
     * @return
     */
    @PostMapping("message/check")
    @ResponseBody
    public AjaxMessage checkMessage(HttpServletRequest request, long message) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        String checkStr = UCPASSUtil.checkMessage(request, message);
        if (checkStr != null) {
            ajaxMessage.setIcon(2);
            ajaxMessage.setMessage(checkStr);
        } else {
            ajaxMessage.setIcon(1);
            ajaxMessage.setMessage("验证成功！");
        }
        return ajaxMessage;
    }

    /**
     * 注册步骤
     *
     * @param id
     * @return
     */
    @GetMapping("regist/{id}")
    public String registStep(@PathVariable String id, HttpServletRequest request) {
        if (id.equals("2")) {
            String content = (String) request.getSession().getAttribute("mobileSend");
            if (content == null || !content.equals("limingyuan")) {
                id = "1";
            }
        }
        return "app/regist/step_" + id;
    }

    /**
     * 找回骤
     *
     * @param id
     * @return
     */
    @GetMapping("rem/{id}")
    public String remStep(@PathVariable String id, HttpServletRequest request) {
        if (id.equals("2")) {
            String content = (String) request.getSession().getAttribute("mobileSend");
            if (content == null || !content.equals("limingyuan")) {
                id = "1";
            }
        }
        return "app/rem/step_" + id;
    }

    /**
     * 查询是否重复
     *
     * @param username
     * @return
     */
    @PostMapping("check/username")
    @ResponseBody
    public AjaxMessage checkUsername(String username) {
        AjaxMessage message = new AjaxMessage();
        if (!userService.checkUsername(username)) {
            message.setMessage("用户名已存在");
            message.setIcon(2);
        } else {
            message.setIcon(1);
            message.setMessage("成功！");
        }
        return message;
    }

    /**
     * 查询是否重复
     *
     * @param mobile
     * @return
     */
    @PostMapping("check/mobile")
    @ResponseBody
    public AjaxMessage checkMobile(String mobile) {
        AjaxMessage message = new AjaxMessage();
        if (!userService.checkMobile(mobile)) {
            message.setMessage("手机号已存在");
            message.setIcon(2);
        } else {
            message.setIcon(1);
            message.setMessage("成功！");
        }
        return message;
    }

    /**
     * 注册操作
     * @param username
     * @param password
     * @param request
     */
    @PostMapping("regist/do")
    @ResponseBody
    public AjaxMessage registDo(String username, String password, String usernick,HttpServletRequest request) {
        AjaxMessage message = new AjaxMessage();
        AppUser appUser = new AppUser();
        String mobile = (String) request.getSession().getAttribute("mobileReg");
        if (mobile == null || mobile.equals("")) {
            message.setIcon(2);
            message.setMessage("请先完成手机验证");
        } else {
            appUser.setUsername(username);
            appUser.setPassword(password);
            appUser.setMobile(mobile);
            appUser.setNickname(usernick);
            String openId = (String) request.getSession().getAttribute("openIdReg");
            if (openId != null && !openId.equals("")) {
                appUser.setOpenid(openId);
            }
            appUser.setType("0");
            userService.saveAppUser(appUser);
            message.setIcon(1);
            message.setMessage("注册成功");
        }
        return message;
    }

    /**
     * 找回操作
     * @param password
     * @param request
     * @return
     */
    @PostMapping("rem/do")
    @ResponseBody
    public AjaxMessage remDo(String password, HttpServletRequest request) {
        AjaxMessage message = new AjaxMessage();
        String mobile = (String) request.getSession().getAttribute("mobileReg");
        if (mobile == null || mobile.equals("")) {
            message.setIcon(2);
            message.setMessage("请先完成手机验证");
        } else {
            if (userService.updateAppUserPWD(mobile, password)) {
                message.setIcon(1);
                message.setMessage("找回密码成功!");
            }
        }
        return message;
    }

    /**
     * 跳转到绑定
     * @param openid
     * @param model
     * @param request
     * @return
     */
    @GetMapping("wechat/regist/{openid}")
    public String wechatReg(@PathVariable String openid, Model model, HttpServletRequest request) {
        if (!userService.checkOpenId(openid)) {
            model.addAttribute("info", "该微信已经绑定账号，请直接登录！");
            return "app/common/_info";
        } else {
            request.getSession().setAttribute("openIdReg", openid);
            return "redirect:/app/regist/1";
        }
    }

    /**
     * 微信登录
     * @param openid
     * @param model
     * @param request
     * @return
     */
    @GetMapping("wechat/login/{openid}")
    public String wechatLogin(@PathVariable String openid, Model model, HttpServletRequest request) {
        if (!userService.checkOpenId(openid)) {
            model.addAttribute("info", "该微信已经注册账号，请直接登录！");
            return "app/common/_info";
        } else {
            request.getSession().setAttribute("wechatLoginOpenid", openid);
            return "app/wechat";
        }
    }

    /**
     * 微信登录操作
     * @param user
     * @param request
     * @return
     */
    @PostMapping("wechat/login/do")
    @ResponseBody
    public AjaxMessage wechatLoginDo(AppUser user, HttpServletRequest request) {
        AjaxMessage message = new AjaxMessage();
        String openId = (String) request.getSession().getAttribute("wechatLoginOpenid");
        if (openId == null || openId.equals("")) {
            message.setIcon(2);
            message.setMessage("微信错误！");
            return message;
        }
        AppUser user1 = userService.selectByUserNamePassword(user.getUsername(), user.getPassword());
        if (user1 == null) {
            message.setIcon(2);
            message.setMessage("用户名或密码错误！");
            return message;
        }

        if (user1.getOpenid() == null || user1.getOpenid().equals("")) {
            if (!userService.checkOpenId(openId)) {
                message.setIcon(2);
                message.setMessage("该微信已绑定账户！");
                return message;
            }
            user1.setOpenid(openId);
            if (userService.updateAll(user1)) {
                message.setIcon(1);
                message.setMessage("绑定成功！请返回微信直接进入首页！");
                return message;
            }
        } else {
            message.setIcon(2);
            message.setMessage("用户已绑定微信账户！");
            return message;
        }


        return message;
    }

    /**
     * 微信主页
     * @param openid
     * @param request
     * @param model
     * @return
     */
    @GetMapping("wechat/index/{openid}")
    public String wechatIndex(@PathVariable String openid, HttpServletRequest request, Model model) {
        AppUser user = userService.selectByOpenId(openid);
        if (user != null) {
            request.getSession().setAttribute("nowAppUser", user);
            model.addAttribute("type","1");
            model.addAttribute("openid",openid);
            return "app/index";
        } else {
            model.addAttribute("info", "错误！您可能还未注册或绑定！");
            return "app/common/_info";
        }
    }

    /**
     * 保存位置
     * @param map
     * @param request
     * @return
     */
    @PostMapping("save/location")
    @ResponseBody
    public AjaxMessage saveMyLocation(String map,HttpServletRequest request){
        AjaxMessage message = new AjaxMessage();
        if(StringUtil.isEmpty(map)){
            message.setIcon(2);
            message.setMessage("获取您的位置失败！请确保您已经共享位置信息！");
            return message;
        }
        request.getSession().setAttribute("nowAppUserLocation",map);
        message.setIcon(1);
        message.setMessage("已获取您的位置！");
        return message;
    }

    /**
     * 跳转修改密码
     * @param model
     * @param request
     * @return
     */
    @GetMapping("password")
    public String setPassword(Model model,HttpServletRequest request){
        model.addAttribute("user", AppUtil.getSessionAppUser(request));
        return "app/user/password";
    }

    /**
     * 检查原始密码是否正确
     * @param password
     * @param request
     * @return
     */
    @PostMapping("password/check")
    @ResponseBody
    public AjaxMessage checkPawword(String password,HttpServletRequest request){
        AjaxMessage message=new AjaxMessage(2,"旧密码输入错误！");
        if(userService.checkPassword(password,request)){
            message.setIcon(1);
            message.setMessage("密码正确！");
            return message;
        }
        return message;
    }

    /**
     * 修改密码操作
     * @param password
     * @param request
     * @return
     */
    @PostMapping("password/do")
    @ResponseBody
    public AjaxMessage passwordDo(String password,HttpServletRequest request){
        AjaxMessage message=new AjaxMessage(2,"密码修改失败！");
        if(userService.updateAppUserPWD(AppUtil.getSessionAppUser(request).getMobile(),password)){
            message.setMessage("密码修改成功！");
            message.setIcon(1);
            return message;
        }
        return message;
    }
}
