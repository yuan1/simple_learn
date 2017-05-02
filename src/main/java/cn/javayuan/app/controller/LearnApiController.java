package cn.javayuan.app.controller;

import cn.javayuan.app.entity.ApiConfigModel;
import cn.javayuan.app.service.LearnApiService;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 接口配置
 * Created by 李明元 on 2017/3/19.
 */
@Controller
@RequestMapping("/learn/api")
public class LearnApiController {
    @Autowired
    private LearnApiService apiService;

    /**
     * 微信接口
     * @param model
     * @return
     */
    @GetMapping("wechat")
    @RequiresPermissions("learn:api:wechat:show")
    public String wechat(Model model){
        model.addAttribute("apis",apiService.selectApiByType("0"));
        return "learn/api/wechat";
    }

    /**
     * 云之讯接口
     * @param model
     * @return
     */
    @GetMapping("ucpaas")
    @RequiresPermissions("learn:api:ucpaas:show")
    public String ucpaas(Model model){
        model.addAttribute("apis",apiService.selectApiByType("1"));
        return "learn/api/ucpaas";
    }

    /**
     * 云之讯接口修改操作
     * @param apiConfigModel
     * @return
     */
    @PostMapping("ucpaas/edit")
    @RequiresPermissions("learn:api:ucpaas:edit")
    @ResponseBody
    public AjaxMessage ucpaasEdit(ApiConfigModel apiConfigModel){
        if(apiService.updateApiList(apiConfigModel.getApis())){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    /**
     * 微信端口修改操作
     * @param apiConfigModel
     * @return
     */
    @PostMapping("wechat/edit")
    @RequiresPermissions("learn:api:wechat:edit")
    @ResponseBody
    public AjaxMessage wechatEdit(ApiConfigModel apiConfigModel){
       if(apiService.updateApiList(apiConfigModel.getApis())){
           return AjaxMessageUtil.updateSuccess();
       }
       return AjaxMessageUtil.updateError();
    }

    /**
     * 七牛云接口
     * @param model
     * @return
     */
    @GetMapping("qiniu/index")
    @RequiresPermissions("learn:api:qiniu:show")
    public String qiniu(Model model){
        model.addAttribute("apis",apiService.selectApiByType("2"));
        return "learn/api/qiniu";
    }
    /**
     * 七牛云接口修改操作
     * @param apiConfigModel
     * @return
     */
    @PostMapping("qiniu/edit")
    @RequiresPermissions("learn:api:qiniu:edit")
    @ResponseBody
    public AjaxMessage qiniuEdit(ApiConfigModel apiConfigModel){
        if(apiService.updateApiList(apiConfigModel.getApis())){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }
    /**
     * 百川接口
     * @param model
     * @return
     */
    @GetMapping("baichuan/index")
    @RequiresPermissions("learn:api:baichuan:show")
    public String baichuan(Model model){
        model.addAttribute("apis",apiService.selectApiByType("3"));
        return "learn/api/baichuan";
    }
    /**
     * 百川接口修改操作
     * @param apiConfigModel
     * @return
     */
    @PostMapping("baichuan/edit")
    @RequiresPermissions("learn:api:baichuan:edit")
    @ResponseBody
    public AjaxMessage baichuanEdit(ApiConfigModel apiConfigModel){
        if(apiService.updateApiList(apiConfigModel.getApis())){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }
    /**
     * 图灵接口
     * @param model
     * @return
     */
    @GetMapping("tuling/index")
    @RequiresPermissions("learn:api:tuling:show")
    public String tuling(Model model){
        model.addAttribute("apis",apiService.selectApiByType("4"));
        return "learn/api/tuling";
    }
    /**
     * 图灵接口修改操作
     * @param apiConfigModel
     * @return
     */
    @PostMapping("tuling/edit")
    @RequiresPermissions("learn:api:tuling:edit")
    @ResponseBody
    public AjaxMessage tulingEdit(ApiConfigModel apiConfigModel){
        if(apiService.updateApiList(apiConfigModel.getApis())){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

}
