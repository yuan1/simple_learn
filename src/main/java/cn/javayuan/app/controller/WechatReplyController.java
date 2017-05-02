package cn.javayuan.app.controller;

import cn.javayuan.app.entity.WechatReply;
import cn.javayuan.app.service.WechatReplyService;
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
 * 微信自定义回复
 * Created by 李明元 on 2017/4/4.
 */
@Controller
@RequestMapping("/learn/wechat")
public class WechatReplyController {
    @Autowired
    private WechatReplyService wechatReplyService;

    /**
     * 列表显示 自定义回复
     * @param reply
     * @param page
     * @param rows
     * @param model
     * @return
     */
    @RequestMapping("reply/index")
    @RequiresPermissions("learn:wechat:reply:show")
    public String reply(WechatReply reply, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int rows,
                        Model model){
        List<WechatReply> wechatReplies=wechatReplyService.selectByWecahtReply(reply,page,rows);
        model.addAttribute("pageInfo", new PageInfo<>(wechatReplies));
        model.addAttribute("queryParam", reply);
        return "learn/wechat/reply/index";
    }

    /**
     * 跳转添加自定义回复
     * @return
     */
    @GetMapping("reply/add")
    @RequiresPermissions("learn:wechat:reply:add")
    public String replyAdd(){
        return "learn/wechat/reply/add";
    }

    /**
     * 执行添加自定义回复
     * @param reply
     * @return
     */
    @PostMapping("reply/add/do")
    @RequiresPermissions("learn:wechat:reply:add")
    @ResponseBody
    public AjaxMessage replyAddDo(WechatReply reply){
        if(wechatReplyService.saveReply(reply)){
            return AjaxMessageUtil.addSuccess();
        }
        return AjaxMessageUtil.addError();
    }

    /**
     * 启用回复
     * @param id
     * @return
     */
    @GetMapping("reply/start/{id}")
    @RequiresPermissions("learn:wechat:reply:start")
    @ResponseBody
    public AjaxMessage replyStart(@PathVariable int id){
        if(wechatReplyService.updateReplyState(id,"0")){
            return AjaxMessageUtil.startSuccess();
        }
        return AjaxMessageUtil.startError();
    }

    /**
     * 停用回复
     * @param id
     * @return
     */
    @GetMapping("reply/stop/{id}")
    @RequiresPermissions("learn:wechat:reply:stop")
    @ResponseBody
    public AjaxMessage replyStop(@PathVariable int id){
        if(wechatReplyService.updateReplyState(id,"1")){
            return AjaxMessageUtil.stopSuccess();
        }
        return AjaxMessageUtil.stopError();
    }

    /**
     * 自定义回复删除
     * @param id
     * @return
     */
    @GetMapping("reply/del/{id}")
    @RequiresPermissions("learn:wechat:reply:del")
    @ResponseBody
    public AjaxMessage replyDel(@PathVariable int id){
        if(wechatReplyService.delete(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }

    /**
     * 自定义回复修改
     * @param id
     * @param model
     * @return
     */
    @GetMapping("reply/edit/{id}")
    @RequiresPermissions("learn:wechat:reply:edit")
    public String replyEdit(@PathVariable int id,Model model){
        model.addAttribute("replyEdit",wechatReplyService.selectByKey(id));
        return "learn/wechat/reply/edit";
    }

    /**
     * 自定义回复修改操作
     * @param reply
     * @return
     */
    @PostMapping("reply/edit/do")
    @RequiresPermissions("learn:wechat:reply:edit")
    @ResponseBody
    public AjaxMessage replyEditDo(WechatReply reply){
        if(wechatReplyService.updateAll(reply)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }
}
