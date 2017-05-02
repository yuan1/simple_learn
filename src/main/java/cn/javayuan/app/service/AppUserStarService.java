package cn.javayuan.app.service;

import cn.javayuan.app.entity.AppUserStar;
import cn.javayuan.system.service.BaseService;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户收藏
 * Created by 李明元 on 2017/4/7.
 */
public interface AppUserStarService  extends BaseService<AppUserStar>{
    boolean saveStar(int findId,HttpServletRequest request);

    boolean deleteStar(int findId, HttpServletRequest request);
}
