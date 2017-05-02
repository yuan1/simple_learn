package cn.javayuan.app.controller;

import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.service.LearnUserService;
import cn.javayuan.app.utils.QiniuUtil;
import cn.javayuan.system.utils.AjaxMessage;
import cn.javayuan.system.utils.AjaxMessageUtil;
import cn.javayuan.system.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 注册用户管理
 * Created by 李明元 on 2017/3/15.
 */
@Controller
@RequestMapping("/learn/user")
public class LearnUserController {

    @Autowired
    private LearnUserService userService;

    /**
     * 用户列表
     *
     * @param model
     * @return
     */
    @RequestMapping("index")
    @RequiresPermissions("learn:user:show")
    public String index(AppUser user, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int rows, Model model) {
        List<AppUser> userList = userService.selectByUser(user, page, rows);
        model.addAttribute("pageInfo", new PageInfo<>(userList));
        model.addAttribute("queryParam", user);
        return "learn/user/index";
    }

    /**
     * 跳转添加用户
     *
     * @return
     */
    @GetMapping("add")
    @RequiresPermissions("learn:user:add")
    public String add() {
        return "learn/user/add";
    }

    /**
     * 添加用户操作
     *
     * @param user
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("add/do")
    @RequiresPermissions("learn:user:add")
    @ResponseBody
    public AjaxMessage addDo(AppUser user, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            String image=QiniuUtil.uploadToQiniu(file.getBytes());
           if(image!=null){
               user.setImage(image);
            }
        }
        if (userService.saveAppUser(user)) {
            return AjaxMessageUtil.addSuccess();
        }
        return AjaxMessageUtil.addError();
    }

    /**
     * 查看用户
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("show/{id}")
    @RequiresPermissions("learn:user:show")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("userShow", userService.selectByKey(id));
        return "learn/user/show";
    }

    /**
     * 跳转修改用户
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    @RequiresPermissions("learn:user:edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("userEdit", userService.selectByKey(id));
        return "learn/user/edit";
    }

    /**
     * 修改用户操作
     *
     * @param user
     * @param filedata
     * @return
     * @throws Exception
     */
    @PostMapping("edit/do")
    @RequiresPermissions("learn:user:edit")
    @ResponseBody
    public AjaxMessage editDo(AppUser user, @RequestParam(value = "file", required = false) MultipartFile filedata) throws Exception {
        if (filedata != null && !filedata.isEmpty()) {
            String image=QiniuUtil.uploadToQiniu(filedata.getBytes());
            if(image!=null){
                QiniuUtil.deleteToQiniu(user.getImage());
                user.setImage(image);
            }
        }
        if (userService.updateAppUser(user)) {
            return AjaxMessageUtil.updateSuccess();
        }
        return AjaxMessageUtil.updateError();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @GetMapping("del/{id}")
    @RequiresPermissions("learn:user:del")
    @ResponseBody
    public AjaxMessage del(@PathVariable int id) {
        if (userService.deleteAppUser(id)) {
            return AjaxMessageUtil.delSuccess();
        }
        return AjaxMessageUtil.delError();
    }
    /**
     * 停用用户
     *
     * @param id
     * @return
     */
    @GetMapping("stop/{id}")
    @RequiresPermissions("learn:user:stop")
    @ResponseBody
    public AjaxMessage stop(@PathVariable int id) {
        if (userService.updateAppUserToStartOrStop(id,"1")) {
            return AjaxMessageUtil.stopSuccess();
        }
        return AjaxMessageUtil.stopError();
    }
    /**
     * 启用用户
     *
     * @param id
     * @return
     */
    @GetMapping("start/{id}")
    @RequiresPermissions("learn:user:start")
    @ResponseBody
    public AjaxMessage start(@PathVariable int id) {
        if (userService.updateAppUserToStartOrStop(id,"0")) {
            return AjaxMessageUtil.stopSuccess();
        }
        return AjaxMessageUtil.startError();
    }

}
