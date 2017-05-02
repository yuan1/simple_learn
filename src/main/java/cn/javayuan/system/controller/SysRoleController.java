/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.controller;

import cn.javayuan.system.entity.SysRole;
import cn.javayuan.system.service.SysConfigService;
import cn.javayuan.system.service.SysRoleService;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import cn.javayuan.system.utils.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制
 * Created by 李明元 on 2016/11/25.
 */
@Controller
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 角色管理主页
     * @return
     */
    @GetMapping("/index")
    @RequiresPermissions("role:show")
    public String index(Model model){
        model.addAttribute("zNodes",sysRoleService.getRoleZtreeJson());
        return "role/index";
    }

    /**
     * 说明
     * @param model
     * @return
     */
    @GetMapping("/info")
    @RequiresPermissions("role:show")
    public String info(Model model){
        model.addAttribute("information",sysConfigService.selectByKey(1).getRoleInfo());
        return "common/_info";
    }

    /**
     * 查看角色
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/show/{id}")
    @RequiresPermissions("role:show")
    public String show(@PathVariable int id,Model model){
        model.addAttribute("roleNow", ShiroUtil.getSessionActiveUser().getSysRole());
        model.addAttribute("roleShow",sysRoleService.findRoleById(id));
        return "role/show";
    }

    /**
     * 跳转道角色添加
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/add/{id}")
    @RequiresPermissions("role:add")
    public String add(@PathVariable int id,Model model){
        model.addAttribute("roleAdd",sysRoleService.selectByKey(id));
        return "role/add";
    }

    /**
     * 保存角色
     * @param role
     * @return
     */
    @PostMapping("/add/do")
    @RequiresPermissions("role:add")
    @ResponseBody
    public AjaxMessage addDo(SysRole role){
        if(sysRoleService.save(role)){
            return AjaxMessageUtil.addSuccess();
        }
        return AjaxMessageUtil.addError();
    }

    /**
     * 跳转道修改角色
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("role:edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute("roleEdit",sysRoleService.findRoleById(id));
        return "role/edit";
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @PostMapping("/edit/do")
    @RequiresPermissions("role:edit")
    @ResponseBody
    public AjaxMessage editDo(SysRole role){
        if(sysRoleService.updateAll(role)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateSuccess();

    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    @RequiresPermissions("role:del")
    @ResponseBody
    public AjaxMessage del(@PathVariable int id){
        if(sysRoleService.deleteRoleById(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }

    /**
     * 跳转修改角色权限
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/permission/edit/{id}")
    @RequiresPermissions("permission:roleEdit")
    public String rolePermissionEdit(@PathVariable int id,Model model){
        model.addAttribute("rolePermissionEdit",sysRoleService.selectByKey(id));
        model.addAttribute("permissionList",sysRoleService.findRoleHasPermissionIds(id));
        model.addAttribute("userPermissionList",sysRoleService.getMenuAndPermissionByUserRole());
        return "role/permission/edit";
    }

    /**
     * 保存修改权限
     * @param roleid
     * @param permissionid
     * @return
     */
    @PostMapping("/permission/edit/do")
    @RequiresPermissions("permission:roleEdit")
    @ResponseBody
    public AjaxMessage rolePermissionEditDo(int roleid,String permissionid){
        if(sysRoleService.saveRolePermission(roleid,permissionid)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }


}
