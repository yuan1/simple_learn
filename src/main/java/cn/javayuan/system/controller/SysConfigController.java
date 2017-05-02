/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.controller;

import cn.javayuan.system.entity.SysConfig;
import cn.javayuan.system.entity.SysUser;
import cn.javayuan.system.service.SysConfigService;
import cn.javayuan.system.service.SysUserService;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import cn.javayuan.system.utils.ShiroUtil;
import cn.javayuan.system.utils.SystemInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统设置
 * Created by 李明元 on 2016/12/3.
 */
@Controller
@RequestMapping("/system")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private SysUserService sysUserService;
    @GetMapping("/info/index")
    @RequiresPermissions("system:info:show")
    public String info(Model model){
        model.addAttribute("systemInfo",sysConfigService.selectByKey(1));
        return "system/info/index";
    }

    @PostMapping("/info/edit")
    @RequiresPermissions("system:info:edit")
    @ResponseBody
    public AjaxMessage infoEdit(SysConfig config){
        if(sysConfigService.updateAll(config)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    @GetMapping("/data/index")
    @RequiresPermissions("system:data")
    public String data(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"druid";
    }

    @GetMapping("welcome")
    public String welcome(Model model,HttpServletRequest request){
        model.addAttribute("systemInfo",sysConfigService.selectByKey(1));
        model.addAttribute("system",new SystemInfo(request));
        return "system/welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"logout";
    }

    @GetMapping("/userinfo")
    @RequiresPermissions("user:info:show")
    public String userInfo(Model model){
        model.addAttribute("userEdit", ShiroUtil.getSessionActiveUser().getSysUser());
        model.addAttribute("userRole", ShiroUtil.getSessionActiveUser().getSysRole());
        return "system/userinfo";
    }
    /**
     * 修改用户操作
     * @param user
     * @return
     */
    @PostMapping("/userinfo/edit")
    @RequiresPermissions("user:info:edit")
    @ResponseBody
    public AjaxMessage editDo(SysUser user){
        if(sysUserService.updateUser(user)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }
}
