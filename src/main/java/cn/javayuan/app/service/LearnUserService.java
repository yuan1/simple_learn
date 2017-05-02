package cn.javayuan.app.service;

import cn.javayuan.app.entity.AppUser;
import cn.javayuan.system.service.BaseService;
import com.taobao.api.ApiException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 注册用户管理
 * Created by 李明元 on 2017/3/15.
 */
public interface LearnUserService extends BaseService<AppUser> {
    boolean saveAppUser(AppUser entity);

    boolean updateAppUser(AppUser user) ;

    AppUser selectByToken(String token);
    AppUser selectByUserNamePassword(String username,String password);


    List<AppUser> selectByUser(AppUser user, int page, int rows);

    boolean updateAppUserToStartOrStop(int id,String type);

    boolean checkUsername(String username);

    boolean checkMobile(String mobile);

    boolean updateAppUserPWD(String mobile, String password) ;

    boolean checkOpenId(String openid);

    AppUser selectByOpenId(String openid);

    boolean deleteAppUser(int id) ;

    boolean updateAppUserLess(AppUser user);

    boolean checkPassword(String password, HttpServletRequest request);

}
