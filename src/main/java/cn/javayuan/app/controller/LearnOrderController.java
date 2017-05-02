package cn.javayuan.app.controller;

import cn.javayuan.app.dto.AppOrderDto;
import cn.javayuan.app.entity.AppUserOrder;
import cn.javayuan.app.service.AppUserOrderService;
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
 * 预约信息
 * Created by 李明元 on 2017/4/14.
 */
@Controller
@RequestMapping("learn/order")
public class LearnOrderController {
    @Autowired
    private AppUserOrderService orderService;

    /**
     * 列表
     * @param page
     * @param rows
     * @param model
     * @return
     */
    @RequestMapping("index")
    @RequiresPermissions("learn:order:show")
    public String index( @RequestParam(required = false, defaultValue = "1") int page,
                         @RequestParam(required = false, defaultValue = "10") int rows,Model model){
        List<AppOrderDto> orderDtos=orderService.selectAllByPage(page,rows);
        model.addAttribute("pageInfo", new PageInfo<>(orderDtos));
        return "learn/order/index";
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @GetMapping("del/{id}")
    @RequiresPermissions("learn:order:del")
    @ResponseBody
    public AjaxMessage del(@PathVariable int id){
        if(orderService.delete(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }
}
