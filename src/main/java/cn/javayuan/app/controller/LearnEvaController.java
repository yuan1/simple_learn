package cn.javayuan.app.controller;

import cn.javayuan.app.dto.AppEvaDto;
import cn.javayuan.app.entity.AppUserEva;
import cn.javayuan.app.service.AppUserEvaService;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评价
 * Created by 李明元 on 2017/4/14.
 */
@Controller
@RequestMapping("learn/eva")
public class LearnEvaController {
    @Autowired
    private AppUserEvaService evaService;
    /**
     * 列表
     * @param page
     * @param rows
     * @param model
     * @return
     */
    @RequestMapping("index")
    @RequiresPermissions("learn:eva:show")
    public String index( @RequestParam(required = false, defaultValue = "1") int page,
                         @RequestParam(required = false, defaultValue = "10") int rows,Model model){
        List<AppEvaDto> evaDtos=evaService.selectAllByPage(page,rows);
        model.addAttribute("pageInfo", new PageInfo<>(evaDtos));
        return "learn/eva/index";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("del/{id}")
    @RequiresPermissions("learn:eva:del")
    @ResponseBody
    public AjaxMessage del(@PathVariable int id){
        if(evaService.delete(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }
    /**
     * 启用
     * @param id
     * @return
     */
    @GetMapping("start/{id}")
    @RequiresPermissions("learn:eva:start")
    @ResponseBody
    public AjaxMessage start(@PathVariable int id){
        if(evaService.updateState(id,"0")){
            return AjaxMessageUtil.startSuccess();
        }
        return AjaxMessageUtil.startError();
    }
    /**
     * 停用
     * @param id
     * @return
     */
    @GetMapping("stop/{id}")
    @RequiresPermissions("learn:eva:stop")
    @ResponseBody
    public AjaxMessage stop(@PathVariable int id){
        if(evaService.updateState(id,"1")){
            return AjaxMessageUtil.stopSuccess();
        }
        return AjaxMessageUtil.stopError();
    }

}
