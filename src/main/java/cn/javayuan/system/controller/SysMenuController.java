/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.controller;

import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.service.SysConfigService;
import cn.javayuan.system.service.SysPermissionService;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单管理
 * Created by 李明元 on 2016/11/23.
 */
@Controller
@RequestMapping("/menu")
public class SysMenuController {
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysConfigService sysConfigService;
    /**
     * 菜单主页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    @RequiresPermissions("menu:show")
    public String index(Model model) {
        model.addAttribute("zNodes", sysPermissionService.getMenuZtreeJson());
        return "menu/index";
    }

    /**
     * 查看菜单
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/show/{id}")
    @RequiresPermissions("menu:show")
    public String show(@PathVariable int id,Model model) {
        model.addAttribute("menuShow",sysPermissionService.findMenuAndPermission(id));
        return "menu/show";
    }

    /**
     * 菜单操作说明
     * @param model
     * @return
     */
    @GetMapping("/info")
    @RequiresPermissions("menu:show")
    public String info(Model model){
        model.addAttribute("information",sysConfigService.selectByKey(1).getMenuInfo());
        return "common/_info";
    }

    /**
     * 添加菜单跳转
     * @param model
     * @return
     */
    @GetMapping("/add")
    @RequiresPermissions("menu:add")
    public String add(Model model){
        model.addAttribute("menuTopTypeList",sysPermissionService.findTopTypeMenuByUserRole());
        return "menu/add";
    }

    /**
     * 添加菜单操作
     * @param menu
     * @return
     */
    @PostMapping("/add/do")
    @RequiresPermissions("menu:add")
    @ResponseBody
    public AjaxMessage addDo(SysPermission menu){
        if(sysPermissionService.savePermission(menu)){
            return AjaxMessageUtil.addSuccess();
        }else {
            return AjaxMessageUtil.addError();
        }
    }

    /**
     * 删除菜单操作
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    @RequiresPermissions("menu:del")
    @ResponseBody
    public AjaxMessage delDo(@PathVariable int id){
        if(sysPermissionService.deleteMenuById(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }

    /**
     * 修改菜单跳转
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("menu:edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute("menuEdit",sysPermissionService.findMenuAndPermission(id));
        return "menu/edit";
    }

    /**
     * 修改菜单操作
     * @param menu
     * @return
     */
    @PostMapping("/edit/do")
    @RequiresPermissions("menu:edit")
    @ResponseBody
    public AjaxMessage editDo(SysPermission menu){
        if(sysPermissionService.updateAll(menu)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }
}
