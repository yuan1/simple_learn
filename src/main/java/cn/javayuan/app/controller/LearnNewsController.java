package cn.javayuan.app.controller;

import cn.javayuan.app.entity.AppNews;
import cn.javayuan.app.service.AppNewService;
import cn.javayuan.app.utils.QiniuUtil;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import cn.javayuan.ucpass.utils.DateUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 发布
 * Created by 李明元 on 2017/4/12.
 */
@Controller
@RequestMapping("learn/news")
public class LearnNewsController {
    @Autowired
    private AppNewService newService;

    /**
     * 主页
     * @param news
     * @param page
     * @param rows
     * @param model
     * @return
     */
    @RequestMapping("index")
    @RequiresPermissions("learn:news:show")
    public String index(AppNews news,@RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int rows,
                        Model model){
        List<AppNews> newsList=newService.selectByNews(news,page,rows);
        model.addAttribute("pageInfo", new PageInfo<>(newsList));
        model.addAttribute("queryParam", news);
        return "learn/news/index";
    }
    /**
     * 上传照片
     * @param filedata
     * @return
     * @throws IOException
     */
    @PostMapping("upload/image")
    @ResponseBody
    public String uploadImage(@RequestParam(value = "wangEditorH5File", required = false) MultipartFile filedata) throws IOException {
        if (filedata != null && !filedata.isEmpty()) {
            String image= QiniuUtil.uploadToQiniu(filedata.getBytes());
            return "http://images.javayuan.cn/"+image;
        }
        return "error|服务器端错误";
    }
    /**
     * 上传缩略图
     * @param filedata
     * @return
     * @throws IOException
     */
    @PostMapping("upload/image/breviary")
    @ResponseBody
    public AjaxMessage uploadImageBreviary( @RequestParam(value = "file", required = false) MultipartFile filedata) throws IOException {
        AjaxMessage message = new AjaxMessage();
        if (filedata != null && !filedata.isEmpty()) {
            String image= QiniuUtil.uploadToQiniu(filedata.getBytes());
            message.setIcon(1);
            message.setMessage(image);
            return message;
        }
        message.setIcon(2);
        message.setMessage("上传失败！");
        return message;
    }

    @GetMapping("add")
    @RequiresPermissions("learn:news:add")
    public String newsAdd(){
        return "learn/news/add";
    }

    /**
     * 添加操作
     * @param news
     * @return
     */
    @PostMapping("add/do")
    @RequiresPermissions("learn:news:add")
    @ResponseBody
    public AjaxMessage newsAddDo(AppNews news){
        news.setCreatTime(DateUtil.dateToStr(new Date()));
        news.setState("0");
        if(newService.save(news)){
            return AjaxMessageUtil.addSuccess();
        }
        return AjaxMessageUtil.addError();
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @GetMapping("start/{id}")
    @RequiresPermissions("learn:news:start")
    @ResponseBody
    public AjaxMessage newsStrat(@PathVariable int id){
        if(newService.updateState(id,"0")){
            return AjaxMessageUtil.startSuccess();
        }
        return AjaxMessageUtil.startError();
    }
    /**
     * 禁止
     * @param id
     * @return
     */
    @GetMapping("stop/{id}")
    @RequiresPermissions("learn:news:stop")
    @ResponseBody
    public AjaxMessage newsStop(@PathVariable int id){
        if(newService.updateState(id,"1")){
            return AjaxMessageUtil.stopSuccess();
        }
        return AjaxMessageUtil.stopError();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @GetMapping("del/{id}")
    @RequiresPermissions("learn:news:del")
    @ResponseBody
    public AjaxMessage newsDel(@PathVariable int id){
        if(newService.delete(id)){
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }

    /**
     * 修改
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    @RequiresPermissions("learn:news:edit")
    public String newsEdit(@PathVariable int id,Model model){
        model.addAttribute("newsEdit",newService.selectByKey(id));
        return "learn/news/edit";
    }

    /**
     * 修改操作
     * @param news
     * @return
     */
    @PostMapping("edit/do")
    @RequiresPermissions("learn:news:edit")
    @ResponseBody
    public AjaxMessage newsEditDo(AppNews news){
        if(newService.updateAll(news)){
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    @GetMapping("show/{id}")
    @RequiresPermissions("learn:news:show")
    public String newsShow(@PathVariable int id,Model model){
        model.addAttribute("newsShow",newService.selectByKey(id));
        return "learn/news/show";
    }
}

