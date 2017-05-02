/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.controller;

import cn.javayuan.system.entity.SysUser;
import cn.javayuan.system.service.SysConfigService;
import cn.javayuan.system.service.SysDeptService;
import cn.javayuan.system.service.SysRoleService;
import cn.javayuan.system.service.SysUserService;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制
 * Created by 李明元 on 2016/11/25.
 */
@Controller
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 主页
     *
     * @param model
     * @return
     */
    @GetMapping("index")
    @RequiresPermissions("user:show")
    public String index(Model model) {
        model.addAttribute("zNodes", sysDeptService.getDeptZtreeJson());
        return "user/index";
    }

    /**
     * 说明
     *
     * @param model
     * @return
     */
    @GetMapping("/info")
    @RequiresPermissions("user:show")
    public String info(Model model) {
        model.addAttribute("information", sysConfigService.selectByKey(1).getUserInfo());
        return "common/_info";
    }

    /**
     * 显示用户列表待添加分页
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/show/{id}")
    @RequiresPermissions("user:show")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("userShowList", sysUserService.findUserByDeptId(id));
        return "user/show";
    }

    /**
     * 跳转添加用户页面
     * @param id 组织机构id
     * @param model
     * @return
     */
    @GetMapping("/add/{id}")
    @RequiresPermissions("user:add")
    public String add(@PathVariable int id, Model model) {
        model.addAttribute("deptAdd",sysDeptService.selectByKey(id));
        model.addAttribute("roleAddList", sysRoleService.findRoleListByUser());
        return "user/add";
    }

    /**
     * 添加用户操作
     * @param user
     * @param deptid
     * @param roleid
     * @return
     */
    @PostMapping("/add/do")
    @RequiresPermissions("user:add")
    @ResponseBody
    public AjaxMessage addDo(SysUser user,int deptid,int roleid){
        if(sysUserService.saveSysUser(user,deptid,roleid)){
            return AjaxMessageUtil.addSuccess();
        }
        return AjaxMessageUtil.addError();
    }

    /**
     * 跳转修改操作
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("user:edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute("userEdit",sysUserService.findSysUserById(id));
        model.addAttribute("deptList",sysDeptService.findDeptListByUser());
        model.addAttribute("roleEditList",sysRoleService.findRoleListByUser());
        return "user/edit";
    }

    /**
     * 修改用户操作
     * @param user
     * @param roleid
     * @param deptid
     * @return
     */
    @PostMapping("/edit/do")
    @RequiresPermissions("user:edit")
    @ResponseBody
    public AjaxMessage editDo(SysUser user,int roleid,int deptid){
        if(sysUserService.updateUserAndRole(user,roleid,deptid)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    @GetMapping("/del/{id}")
    @RequiresPermissions("user:del")
    @ResponseBody
    public AjaxMessage del(@PathVariable int id){
        if(sysUserService.deleteUserById(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }

}
