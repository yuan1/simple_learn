package cn.javayuan.app.service.impl;

import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.entity.AppUserValue;
import cn.javayuan.app.mapper.AppUserMapper;
import cn.javayuan.app.mapper.AppUserValueMapper;
import cn.javayuan.app.service.AppUserValueService;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import cn.javayuan.ucpass.utils.DateUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * app user扩展属性api接口
 * Created by 李明元 on 2017/3/19.
 */
@Service
public class AppUserValueServiceImpl extends BaseServiceImpl<AppUserValue> implements AppUserValueService{

    @Autowired
    private AppUserValueMapper valueMapper;

    @Autowired
    private AppUserMapper userMapper;

    /**
     * 查询token
     * @param userId
     * @return
     */
    @Override
    public AppUserValue selectValueByUserId(int userId,String name) {
        Example example=new Example(AppUserValue.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("appUserId",userId);
        criteria.andEqualTo("name",name);
        List<AppUserValue> values=mapper.selectByExample(example);
        if(values.size()>0){
            return values.get(0);
        }
        return null;
    }

    /**
     * 保存
     * @param value
     * @param id
     * @param name
     * @return
     */
    @Override
    public boolean saveValue(String value, int id, String name) {
        AppUserValue userValue= new AppUserValue();
        userValue.setAppUserId(id);
        userValue.setCreateTime(DateUtil.dateToStr(new Date()));
        userValue.setName(name);
        userValue.setUserValue(value);
        return save(userValue);
    }

    /**
     * 获取会话列表
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> selectChatList(HttpServletRequest request) {
        AppUser user= AppUtil.getSessionAppUser(request);
        List<String> list=valueMapper.selectValueDistinct("chatList",user.getId());
        LOG.info(list);
        List<String> list2=valueMapper.selectUserIdDistinct("chatList",user.getId().toString());
        LOG.info(list2);
        List<String> newList =new ArrayList<>();
        for(String s:list2){
            if(!list.contains(s)){
                newList.add(s);
            }
        }
        LOG.info(newList);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for(String s:list){
            AppUserValue value =valueMapper.selectTopCreate("chatList",user.getId(),s);
            AppUser toUser=userMapper.selectByPrimaryKey(Integer.valueOf(value.getUserValue()));
            Map<String, Object> map = new HashedMap();
            map.put("toUserId",value.getUserValue());
            map.put("toUserName",toUser.getUsername());
            map.put("toUserNick",toUser.getNickname());
            map.put("toUserImage",toUser.getImage());
            map.put("toUserTime",value.getCreateTime());
            mapList.add(map);
        }
        for(String s:newList){
            AppUserValue value =valueMapper.selectTopCreateRe("chatList",user.getId(),s);
            AppUser toUser=userMapper.selectByPrimaryKey(value.getAppUserId());
            Map<String, Object> map = new HashedMap();
            map.put("toUserId",value.getAppUserId());
            map.put("toUserName",toUser.getUsername());
            map.put("toUserNick",toUser.getNickname());
            map.put("toUserImage",toUser.getImage());
            map.put("toUserTime",value.getCreateTime());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 保存反馈
     * @param request
     * @param feedback
     * @return
     */
    @Override
    public boolean saveFeedback(HttpServletRequest request, String feedback) {
        int userId=AppUtil.getSessionUserId(request);
        return saveValue(feedback,userId,"feedback");
    }
}
