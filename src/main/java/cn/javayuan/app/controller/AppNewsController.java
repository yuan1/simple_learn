package cn.javayuan.app.controller;

import cn.javayuan.app.service.AppNewService;
import com.sdicons.json.validator.impl.predicates.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * app发现模块
 * Created by 李明元 on 2017/4/6.
 */
@Controller
@RequestMapping("app/news")
public class AppNewsController {
    @Autowired
    private AppNewService newService;
    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("newsList",newService.selectAllNews());
        return "app/news/index";
    }

    @GetMapping("show/{id}")
    public String newsShow(@PathVariable int id,Model model){
        model.addAttribute("news",newService.selectByKey(id));
        return "app/news/show";
    }
}
