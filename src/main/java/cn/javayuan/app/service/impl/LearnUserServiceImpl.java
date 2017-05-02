package cn.javayuan.app.service.impl;

import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.service.LearnUserService;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.app.utils.QiniuUtil;
import cn.javayuan.app.utils.TaobaoBCClientUtil;
import cn.javayuan.ucpass.utils.DateUtil;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import cn.javayuan.system.utils.ShiroUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * app用户
 * Created by 李明元 on 2017/3/16.
 */
@Service
public class LearnUserServiceImpl extends BaseServiceImpl<AppUser> implements LearnUserService {


    /**
     * 保存用户
     * @param user
     * @return
     */
    @Override
    public boolean saveAppUser(AppUser user)  {
       if(checkUsername(user.getUsername())){
           user.setSalt(ShiroUtil.createSalt());
           user.setToken(ShiroUtil.createSalt());
           String pwd=ShiroUtil.createEncodedPassword(user.getPassword(), user.getSalt());
           user.setPassword(pwd);
           user.setCreat(DateUtil.dateToStr(new Date()));
           if(user.getImage()==null||user.getImage().equals("")){
               user.setImage("default.png");
           }
           TaobaoBCClientUtil.addUserToTaobaoBC(user);
           return save(user);
       }else {
           return false;
       }
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public boolean updateAppUser(AppUser user)  {
        AppUser oldUser=selectByKey(user.getId());
        if(user.getPassword()!=null&&!user.getPassword().equals("")){
            user.setPassword(ShiroUtil.createEncodedPassword(user.getPassword(), oldUser.getSalt()));
        }else {
            user.setPassword(oldUser.getPassword());
        }
        user.setToken(ShiroUtil.createSalt());
        TaobaoBCClientUtil.updateUserToTaoBaoBc(user);
        return updateAll(user);
    }

    /**
     * 通过token查询用户
     * @param token
     * @return
     */
    @Override
    public AppUser selectByToken(String token) {
        Example example = new Example(AppUser.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("token",token);
        criteria.andEqualTo("type","0");
        List<AppUser> appUsers =selectByExample(example);
        if(appUsers.size()>0){
            return appUsers.get(0);
        }
        return null;
    }

    /**
     * 通过用户名和密码查询可用用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public AppUser selectByUserNamePassword(String username, String password) {
        Example example = new Example(AppUser.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("type","0");
        List<AppUser> appUsers =selectByExample(example);
        if(appUsers.size()<=0){
            Example example1 = new Example(AppUser.class);
            Example.Criteria criteria1= example1.createCriteria();
            criteria1.andEqualTo("mobile",username);
            criteria1.andEqualTo("type","0");
            appUsers =selectByExample(example1);
        }
        for(AppUser user:appUsers){
            String checkPWD=ShiroUtil.createEncodedPassword(password, user.getSalt());
            if(user.getPassword().equals(checkPWD)){
                return user;
            }
        }
        return null;
    }

    /**
     * 查询用户
     * @param user
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<AppUser> selectByUser(AppUser user, int page, int rows) {
        Example example = new Example(AppUser.class);
        Example.Criteria criteria =example.createCriteria();
        if(StringUtil.isNotEmpty(user.getUsername())){
            criteria.andLike("username","%"+user.getUsername()+"%");
        }
        if(StringUtil.isNotEmpty(user.getType())){
            criteria.andEqualTo("type",user.getType());
        }
        if(StringUtil.isNotEmpty(user.getMobile())){
            criteria.andLike("mobile","%"+user.getMobile()+"%");
        }
        if(StringUtil.isNotEmpty(user.getNickname())){
            criteria.andLike("nickname","%"+user.getNickname()+"%");
        }
        if(StringUtil.isNotEmpty(user.getCreat())){
            criteria.andLike("creat","%"+user.getCreat()+"%");
        }
        example.orderBy("username");
        PageHelper.startPage(page,rows);
        return selectByExample(example);
    }

    /**
     * 启用或停用用户
     * @param id
     * @param type
     * @return
     */
    @Override
    public boolean updateAppUserToStartOrStop(int id,String type) {
        AppUser user=selectByKey(id);
        user.setType(type);
        return updateAll(user);
    }

    /**
     * 检查用户名
     * @param username
     * @return
     */
    @Override
    public boolean checkUsername(String username){
        Example example = new Example(AppUser.class);
        example.createCriteria().andEqualTo("username",username);
        List<AppUser> appUsers =selectByExample(example);
        if(appUsers.size() > 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 检查手机号码
     * @param mobile
     * @return
     */
    @Override
    public boolean checkMobile(String mobile) {
        Example example = new Example(AppUser.class);
        example.createCriteria().andEqualTo("mobile",mobile);
        List<AppUser> appUsers =selectByExample(example);
        if(appUsers.size() > 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 修改用户密码
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public boolean updateAppUserPWD(String mobile, String password) {
        Example example = new Example(AppUser.class);
        example.createCriteria().andEqualTo("mobile",mobile);
        List<AppUser> appUsers =selectByExample(example);
        AppUser appUser =appUsers.get(0);
        appUser.setPassword(password);
        return updateAppUser(appUser);
    }

    /**
     * 检查微信openid
     * @param openid
     * @return
     */
    @Override
    public boolean checkOpenId(String openid) {
        Example example = new Example(AppUser.class);
        example.createCriteria().andEqualTo("openid",openid);
        List<AppUser> appUsers =selectByExample(example);
        if(appUsers.size() > 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 通过微信openid查询
     * @param openid
     * @return
     */
    @Override
    public AppUser selectByOpenId(String openid) {
        Example example = new Example(AppUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openid",openid);
        criteria.andEqualTo("type","0");
        List<AppUser> appUsers =selectByExample(example);
        if(appUsers.size() > 0){
            return appUsers.get(0);
        }else {
            return null;
        }
    }

    /**
     * 删除qpp用户，同时删除七牛云存储中的图片
     * @param id
     * @return
     */
    @Override
    public boolean deleteAppUser(int id)  {
        AppUser user=selectByKey(id);
        QiniuUtil.deleteToQiniu(user.getImage());
        TaobaoBCClientUtil.delUserToTaoBaoBC(user);
        return delete(user.getId());
    }

    /**
     * 简单更新用户
     * @param user
     * @return
     */
    @Override
    public boolean updateAppUserLess(AppUser user) {
        AppUser userOld=selectByKey(user.getId());
        userOld.setNickname(user.getNickname());
        userOld.setEmail(user.getEmail());
        if(!user.getImage().equals(userOld.getImage())){
            QiniuUtil.deleteToQiniu(user.getImage());
            userOld.setImage(user.getImage());
        }
        userOld.setSex(user.getSex());
        return updateAll(userOld);
    }

    /**
     * 检查原密码
     * @param password
     * @param request
     * @return
     */
    @Override
    public boolean checkPassword(String password, HttpServletRequest request) {
        AppUser user= AppUtil.getSessionAppUser(request);
        String old=ShiroUtil.createEncodedPassword(password, user.getSalt());
        System.out.println(old);
        return old.equals(user.getPassword());
    }

}
