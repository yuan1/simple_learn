package cn.javayuan.app.service;

import cn.javayuan.app.entity.AppNews;
import cn.javayuan.system.service.BaseService;

import java.util.List;

/**
 * 新闻发布
 * Created by 李明元 on 2017/4/13.
 */
public interface AppNewService extends BaseService<AppNews> {
    List<AppNews> selectByNews(AppNews news, int page, int rows);

    boolean updateState(int id, String s);

    List<AppNews> selectAllNews();
}
