package cn.javayuan.app.service.impl;

import cn.javayuan.app.entity.AppUserStar;
import cn.javayuan.app.service.AppUserStarService;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import cn.javayuan.ucpass.utils.DateUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户收藏
 * Created by 李明元 on 2017/4/7.
 */
@Service
public class AppUserStarServiceImpl extends BaseServiceImpl<AppUserStar> implements AppUserStarService {

    /**
     * 保存收藏
     * @param findId
     * @param request
     * @return
     */
    @Override
    public boolean saveStar(int findId, HttpServletRequest request) {
        AppUserStar star=new AppUserStar();
        star.setUserId(AppUtil.getSessionAppUser(request).getId());
        star.setFindId(findId);
        star.setCreatTime(DateUtil.dateToStr(new Date()));
        return save(star);
    }

    /**
     * 取消收藏
     * @param findId
     * @param request
     * @return
     */
    @Override
    public boolean deleteStar(int findId, HttpServletRequest request) {
        int userId=AppUtil.getSessionAppUser(request).getId();
        Example example = new Example(AppUserStar.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("findId",findId);
        int rs=mapper.deleteByExample(example);
        return rs>0;
    }
}
