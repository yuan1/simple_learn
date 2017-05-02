/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.controller;

import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.service.SysPermissionService;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 权限控制
 * Created by 李明元 on 2016/11/24.
 */
@Controller
@RequestMapping("/permission")
public class SysPermissionController {
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 跳转道修改权限页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        SysPermission sysPermission=sysPermissionService.selectByKey(id);
        model.addAttribute("permissionEdit",sysPermission);
        model.addAttribute("permissionMenu",sysPermissionService.selectByKey(sysPermission.getParentid()));
        return "permission/edit";
    }

    /**
     * 执行修改权限操作
     * @param permission
     * @return
     */
    @PostMapping("/edit/do")
    @RequiresPermissions("permission:edit")
    @ResponseBody
    public AjaxMessage editDo(SysPermission permission){
        if(sysPermissionService.updateAll(permission)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    /**
     * 跳转道添加权限页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/add/{id}")
    @RequiresPermissions("permission:add")
    public String add(@PathVariable int id,Model model){
        model.addAttribute("permissionAdd",sysPermissionService.selectByKey(id));
        return "permission/add";
    }

    /**
     * 执行添加权限操作
     * @param permission
     * @return
     */
    @PostMapping("/add/do")
    @RequiresPermissions("permission:add")
    @ResponseBody
    public AjaxMessage addDo(SysPermission permission){
        permission.setType(2);
        if(sysPermissionService.savePermission(permission)){
            return AjaxMessageUtil.addSuccess();
        }
        return AjaxMessageUtil.addError();
    }


}
