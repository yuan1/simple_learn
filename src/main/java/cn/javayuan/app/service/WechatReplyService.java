package cn.javayuan.app.service;

import cn.javayuan.app.entity.WechatReply;
import cn.javayuan.system.service.BaseService;

import java.util.List;

/**
 * 微信自定义回复
 * Created by 李明元 on 2017/4/4.
 */
public interface WechatReplyService extends BaseService<WechatReply> {
    List<WechatReply> selectByWecahtReply(WechatReply reply, int page, int rows);

    boolean saveReply(WechatReply reply);

    boolean updateReplyState(int id,String state);

    String selectReplyByName(String name);
}
