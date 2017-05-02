package cn.javayuan.app.controller;

import cn.javayuan.app.entity.AppFind;
import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.service.AppUserOrderService;
import cn.javayuan.app.service.AppUserStarService;
import cn.javayuan.app.service.LearnFindService;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 找服务
 * Created by 李明元 on 2017/3/17.
 */
@Controller
@RequestMapping("/app/find")
public class AppFindController {

    @Autowired
    private LearnFindService findService;

    @Autowired
    private AppUserStarService starService;

    @Autowired
    private AppUserOrderService orderService;

    @GetMapping("list/index")
    public String list(Model model){
        model.addAttribute("type","1");
        return "app/find/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Map<String, Object>> listContent(@RequestParam(required = false, defaultValue = "0") int state, String type, HttpServletRequest request) {
        return findService.selectFindByType(request,type,state);
    }

    @GetMapping("search")
    public String findSearch(){
        return "app/find/search";
    }
    @RequestMapping("search/do")
    @ResponseBody
    public List<Map<String, Object>> searchContent(String search, HttpServletRequest request) {
        return findService.selectFindBySearch(request,search);
    }


    @GetMapping("map/{type}")
    @ResponseBody
    public List<Map<String, Object>> mapContent(@PathVariable String type, HttpServletRequest request) {
        return findService.selectFindByTypeToMap(request,type);
    }

    @GetMapping("show/{id}")
    public String findShow(@PathVariable int id,Model model,HttpServletRequest request) {
        if(findService.checkFind(id)){
            model.addAttribute("info","该信息已删除！");
            return "app/common/_info";
        }
        if(findService.checkFind2(id)){
            model.addAttribute("info","该信息已被管理员禁止！");
            return "app/common/_info";
        }
        model.addAttribute("find",findService.selectFindDataById(id,request));
        return "app/find/show";
    }

    /**
     * 添加find
     * @param request
     * @param model
     * @return
     */
    @GetMapping("add")
    public String addFind(HttpServletRequest request, Model model) {
        String nowAppUserLocation=AppUtil.getSessionAppUserLocation(request);
        if(nowAppUserLocation==null){
            model.addAttribute("info", "请等待定位成功后再操作！");
            return "app/common/_info";
        }
        AppUser appUser = AppUtil.getSessionAppUser(request);
        String findIdCreat = findService.createFind(appUser,nowAppUserLocation);
        if (findIdCreat == null) {
            model.addAttribute("info", "系统错误！");
            return "app/common/_info";
        }
        model.addAttribute("findId", findIdCreat);
        return "app/find/add";
    }
    @GetMapping("edit/{findId}")
    public String editFind(@PathVariable String findId,Model model){
        model.addAttribute("findId", findId);
        return "app/find/add";
    }

    @PostMapping("del")
    @ResponseBody
    public AjaxMessage delFind(int id){
        AjaxMessage message =new AjaxMessage();
        if(findService.deleteFind(id)){
            message.setIcon(1);
            message.setMessage("删除成功！");
            return message;
        }
        message.setIcon(2);
        message.setMessage("删除失败！");
        return message;
    }
    @PostMapping("add/get")
    @ResponseBody
    public AppFind addGetFind(String findId){
        return findService.selectFindByFindId(findId);
    }
    /**
     * 跳转获取地点
     * @param findId
     * @param model
     * @return
     */
    @GetMapping("map/address/{findId}")
    public String getMapaddress(@PathVariable String findId, Model model) {
        model.addAttribute("findId", findId);
        return "app/find/map_address";
    }

    @PostMapping("update")
    @ResponseBody
    public AjaxMessage updateByFindId(AppFind find){
        if(findService.updateByFindId(find)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    /**
     * 获取位置更新find
     * @param findId
     * @param poiaddress
     * @return
     */
    @PostMapping("map/address/select")
    @ResponseBody
    public AjaxMessage selectMapAddress(String findId, String poiaddress, String map) {
        AjaxMessage message = new AjaxMessage();
        if (StringUtil.isEmpty(findId)) {
            message.setIcon(2);
            message.setMessage("错误！请返回重新发布！");
            return message;
        }
        if (StringUtil.isEmpty(poiaddress) || StringUtil.isEmpty(map) ) {
            message.setIcon(2);
            message.setMessage("请选择位置");
            return message;
        }
        if (findService.updateFindAddressMap(findId, poiaddress, map)) {
            message.setIcon(1);
            message.setMessage("设置成功！");
            return message;
        }

        message.setIcon(2);
        message.setMessage("失败！");
        return message;
    }

    /**
     * 发布操作
     * @param find
     * @return
     */
    @PostMapping("add/do")
    @ResponseBody
    public AjaxMessage addDo(AppFind find){
        AjaxMessage message =new AjaxMessage();
        if(findService.updateFindByFindId(find)){
            message.setIcon(1);
            message.setMessage("发布成功！");
            return  message;
        }
        message.setIcon(2);
        message.setMessage("发布失败！");
        return message;
    }

    /**
     * 收藏
     * @param findId
     * @param state
     * @param request
     * @return
     */
    @PostMapping("star")
    @ResponseBody
    public AjaxMessage starFind(int findId,String state,HttpServletRequest request){
        AjaxMessage message= new AjaxMessage();
        if(state.equals("0")){
            if(starService.saveStar(findId,request)){
                message.setIcon(1);
                message.setMessage("收藏成功！");
                return message;
            }
        }else {
            if(starService.deleteStar(findId,request)){
                message.setIcon(1);
                message.setMessage("取消收藏成功！");
                return message;
            }
        }
        message.setIcon(2);
        message.setMessage("收藏失败！");
        return message;
    }

    @PostMapping("order")
    @ResponseBody
    public AjaxMessage orderFind(int findId,HttpServletRequest request){
        AjaxMessage message = new AjaxMessage();
        String orderId=orderService.saveOrder(findId,AppUtil.getSessionUserId(request));
        if(orderId!=null){
            message.setMessage(orderId);
            message.setIcon(1);
            return message;
        }
        message.setIcon(2);
        message.setMessage("预约失败！");
        return message;

    }




}
