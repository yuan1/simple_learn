/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.controller;

import cn.javayuan.system.entity.SysDept;
import cn.javayuan.system.service.SysConfigService;
import cn.javayuan.system.service.SysDeptService;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import cn.javayuan.system.utils.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 组织机构控制
 * Created by 李明元 on 2016/11/25.
 */
@Controller
@RequestMapping("/dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysConfigService sysConfigService;
    /**
     * 首页跳转
     * @param model
     * @return
     */
    @GetMapping("/index")
    @RequiresPermissions("dept:show")
    public String index(Model model){
        model.addAttribute("zNodes",sysDeptService.getDeptZtreeJson());
        return "dept/index";
    }

    /**
     * 查看组织机构
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/show/{id}")
    @RequiresPermissions("dept:show")
    public String show(@PathVariable int id,Model model){
        model.addAttribute("deptNow", ShiroUtil.getSessionActiveUser().getSysDept());
        model.addAttribute("deptShow",sysDeptService.findDeptById(id));
        return "dept/show";
    }

    /**
     * 组织机构操作说明
     * @param model
     * @return
     */
    @GetMapping("/info")
    @RequiresPermissions("dept:show")
    public String info(Model model){
        model.addAttribute("information",sysConfigService.selectByKey(1).getDeptInfo());
        return "common/_info";
    }

    /**
     * 跳转组织机构添加页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/add/{id}")
    @RequiresPermissions("dept:add")
    public String add(@PathVariable int id,Model model){
        model.addAttribute("deptAdd",sysDeptService.selectByKey(id));
        return "dept/add";
    }

    /**
     * 保存组织机构操作
     * @param sysDept
     * @return
     */
    @PostMapping("/add/do")
    @RequiresPermissions("dept:add")
    @ResponseBody
    public AjaxMessage addDo(SysDept sysDept){
        if(sysDeptService.save(sysDept)){
            return AjaxMessageUtil.addSuccess();
        }
        return AjaxMessageUtil.addError();
    }

    /**
     * 跳转修改组织机构
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("dept:edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute("deptEdit",sysDeptService.findDeptById(id));
        return "dept/edit";
    }


    @PostMapping("/edit/do")
    @RequiresPermissions("dept:edit")
    @ResponseBody
    public AjaxMessage editDo(SysDept dept){
        if(sysDeptService.updateAll(dept)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    /**
     * 删除组织机构
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    @RequiresPermissions("dept:del")
    @ResponseBody
    public AjaxMessage del(@PathVariable int id){
        if(sysDeptService.deleteDeptById(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delSuccess();
    }
}
