package cn.javayuan.app.service.impl;

import cn.javayuan.app.entity.AppNews;
import cn.javayuan.app.service.AppNewService;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * 新闻
 * Created by 李明元 on 2017/4/13.
 */
@Service
public class AppNewServiceImpl extends BaseServiceImpl<AppNews> implements AppNewService {
    /**
     * 条件查询
     * @param news
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<AppNews> selectByNews(AppNews news, int page, int rows) {
        Example example = new Example(AppNews.class);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotEmpty(news.getTitle())){
            criteria.andLike("title","%"+news.getTitle()+"%");
        }
        if(StringUtil.isNotEmpty(news.getState())){
            criteria.andEqualTo("state",news.getState());
        }
        if(StringUtil.isNotEmpty(news.getCreatTime())){
            criteria.andLike("creatTime","%"+news.getCreatTime()+"%");
        }
        example.orderBy("creatTime");
        PageHelper.startPage(page,rows);
        return selectByExample(example);
    }

    /**
     * 更新状态
     * @param id
     * @param s
     * @return
     */
    @Override
    public boolean updateState(int id, String s) {
        AppNews news=selectByKey(id);
        news.setState(s);
        return updateAll(news);
    }

    /**
     * 查询全部启用信息
     * @return
     */
    @Override
    public List<AppNews> selectAllNews() {
        Example example = new Example(AppNews.class);
        example.createCriteria().andEqualTo("state","0");
        example.orderBy("creatTime").desc();
        return selectByExample(example);
    }
}
