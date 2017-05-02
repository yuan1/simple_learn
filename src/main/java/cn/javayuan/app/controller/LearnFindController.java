package cn.javayuan.app.controller;

import cn.javayuan.app.dto.AppFindDto;
import cn.javayuan.app.entity.AppFind;
import cn.javayuan.app.service.LearnFindService;
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
 * 约学信息
 * Created by 李明元 on 2017/4/6.
 */
@Controller
@RequestMapping("learn/find")
public class LearnFindController {
    @Autowired
    private LearnFindService findService;

    /**
     * 列表
     * @param find
     * @param page
     * @param rows
     * @param model
     * @return
     */
    @RequestMapping("index")
    @RequiresPermissions("learn:find:show")
    public String index(AppFind find , @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int rows,Model model){
        List<AppFindDto> findDtos=findService.selectByFind(find,page,rows);
        model.addAttribute("pageInfo", new PageInfo<>(findDtos));
        model.addAttribute("queryParam", find);
        return "learn/find/index";
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @GetMapping("start/{id}")
    @RequiresPermissions("learn:find:start")
    @ResponseBody
    public AjaxMessage start(@PathVariable int id){
        if(findService.updateFindState(id,"1")){
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
    @RequiresPermissions("learn:find:stop")
    @ResponseBody
    public AjaxMessage stop(@PathVariable int id){
        if(findService.updateFindState(id,"2")){
            return AjaxMessageUtil.stopSuccess();
        }
        return AjaxMessageUtil.stopError();
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @GetMapping("del/{id}")
    @RequiresPermissions("learn:find:del")
    @ResponseBody
    public AjaxMessage del(@PathVariable int id){
        if(findService.deleteFindAll(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }

    /**
     * 查看
     * @param id
     * @param model
     * @return
     */
    @GetMapping("show/{id}")
    @RequiresPermissions("learn:find:show")
    public String show(@PathVariable int id,Model model){
        model.addAttribute("find",findService.selectFindDtoByKey(id));
        return "learn/find/show";
    }

    /**
     * 修改
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    @RequiresPermissions("learn:find:edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute("find",findService.selectByKey(id));
        return "learn/find/edit";
    }

    /**
     * 修改操作
     * @param find
     * @return
     */
    @PostMapping("edit/do")
    @RequiresPermissions("learn:find:edit")
    @ResponseBody
    public AjaxMessage editDo(AppFind find){
        if(findService.updateAll(find)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }
}
