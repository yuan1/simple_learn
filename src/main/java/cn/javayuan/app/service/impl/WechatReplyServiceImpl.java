package cn.javayuan.app.service.impl;

import cn.javayuan.app.entity.WechatReply;
import cn.javayuan.app.service.WechatReplyService;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import cn.javayuan.ucpass.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * 微信自定义回复
 * Created by 李明元 on 2017/4/4.
 */

@Service
public class WechatReplyServiceImpl extends BaseServiceImpl<WechatReply> implements WechatReplyService{
    /**
     * 自定义查询回复
     * @param reply
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<WechatReply> selectByWecahtReply(WechatReply reply, int page, int rows) {
        Example example = new Example(WechatReply.class);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotEmpty(reply.getName())){
            criteria.andLike("name","%"+reply.getName()+"%");
        }
        if(StringUtil.isNotEmpty(reply.getReply())){
            criteria.andLike("reply","%"+reply.getReply()+"%");
        }
        if(StringUtil.isNotEmpty(reply.getState())){
            criteria.andEqualTo("state",reply.getState());
        }
        if(StringUtil.isNotEmpty(reply.getCreat())){
            criteria.andLike("creat","%"+reply.getCreat()+"%");
        }
        example.orderBy("create");
        PageHelper.startPage(page,rows);
        return selectByExample(example);
    }

    /**
     * 保存
     * @param reply
     * @return
     */
    @Override
    public boolean saveReply(WechatReply reply) {
        reply.setCreat(DateUtil.dateToStr(new Date()));
        reply.setState("0");
        return save(reply);
    }

    /**
     * 更新状态
     * @param id
     * @return
     */
    @Override
    public boolean updateReplyState(int id,String state) {
        WechatReply reply=selectByKey(id);
        reply.setState(state);
        return updateAll(reply);
    }

    /**
     * 通过关键字查询回复
     * @param name
     * @return
     */
    @Override
    public String selectReplyByName(String name) {
        Example example = new Example(WechatReply.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("name",name);
        criteria.andEqualTo("state","0");
        List<WechatReply> replys=selectByExample(example);
        if(replys!=null&&replys.size()>0){
            return replys.get(0).getReply();
        }
        return null;
    }
}
