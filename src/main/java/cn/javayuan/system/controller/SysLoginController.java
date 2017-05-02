package cn.javayuan.system.controller;

import cn.javayuan.system.dto.UseUserDto;
import cn.javayuan.system.service.SysConfigService;
import cn.javayuan.system.utils.ShiroUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制
 * Created by 李明元 on 2016/11/22.
 */
@Controller
public class SysLoginController {
    @Autowired
    private SysConfigService sysConfigService;
    /**
     * 主页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        UseUserDto useUserDto = ShiroUtil.getSessionActiveUser();
        //通过model传到页面
        model.addAttribute("useUserDto", useUserDto);
        model.addAttribute("sysConfig",sysConfigService.selectByKey(1));
        return "index";
    }

    /**
     * 登录操作在realm
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("sysConfig",sysConfigService.selectByKey(1));
        //如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                model.addAttribute("info", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                model.addAttribute("info", "用户名/密码错误");
            } else {
                throw new Exception();//最终在异常处理器生成未知错误
            }
        }
        //此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
        //登陆失败还到login页面
        return "login";
    }
}
