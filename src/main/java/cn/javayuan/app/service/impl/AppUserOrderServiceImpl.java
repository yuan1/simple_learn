package cn.javayuan.app.service.impl;

import cn.javayuan.app.dto.AppOrderDto;
import cn.javayuan.app.entity.AppFind;
import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.entity.AppUserOrder;
import cn.javayuan.app.service.AppUserOrderService;
import cn.javayuan.app.service.LearnFindService;
import cn.javayuan.app.service.LearnUserService;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import cn.javayuan.ucpass.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户预约订单
 * Created by 李明元 on 2017/4/7.
 */
@Service
public class AppUserOrderServiceImpl extends BaseServiceImpl<AppUserOrder> implements AppUserOrderService {

    @Autowired
    private LearnFindService findService;

    @Autowired
    private LearnUserService userService;

    /**
     * 保存预约
     * @param findId
     * @param sessionUserId
     * @return
     */
    @Override
    public String saveOrder(int findId, int sessionUserId) {
        AppFind find = findService.selectByKey(findId);
        AppUserOrder order = new AppUserOrder();
        order.setUserId(sessionUserId);
        order.setFindId(findId);
        order.setFindUserId(find.getAppUserId());
        order.setCreatTime(DateUtil.dateToStr(new Date()));
        order.setState("0");
        order.setOrderId(DateUtil.dateToStr(new Date(), 5));
        if (save(order)) {
            return order.getOrderId();
        }
        return null;
    }

    /**
     * 通过id查询预约信息
     * @param orderId
     * @return
     */
    @Override
    public AppUserOrder selectOrderByOrderId(String orderId) {
        Example example = new Example(AppUserOrder.class);
        example.createCriteria().andEqualTo("orderId", orderId);
        List<AppUserOrder> orders = selectByExample(example);
        if (orders.size() > 0) {
            return orders.get(0);
        }
        return null;
    }

    /**
     * 通过id查询预约信息
     * @param orderId
     * @return
     */
    @Override
    public Map<String, Object> selectOrder(int orderId) {
        AppUserOrder order = selectByKey(orderId);
        Map<String, Object> map = new HashedMap();
        if (order != null) {
            AppUser fromUser = userService.selectByKey(order.getUserId());
            AppUser toUser = userService.selectByKey(order.getFindUserId());
            AppFind find = findService.selectByKey(order.getFindId());
            map.put("fromUserId", fromUser.getId());
            map.put("fromUserNick", fromUser.getNickname());
            map.put("fromUserName", fromUser.getUsername());
            map.put("fromUserMobile", fromUser.getMobile());
            map.put("toUserNick", toUser.getNickname());
            map.put("toUserName", toUser.getUsername());
            map.put("toUserId", toUser.getId());
            map.put("toUserMobile", toUser.getMobile());
            map.put("findId", find.getId());
            map.put("findName", find.getName());
            map.put("findLess", cn.javayuan.app.utils.DateUtil.checkDate(find.getFindTime()));
            map.put("orderStateNum", order.getState());
            map.put("orderCreate", order.getCreatTime());
            map.put("orderNum", order.getOrderId());
        }else {
            map.put("now", "没有查询到");
        }
        return map;
    }

    /**
     * 我的预约
     *
     * @param sessionUserId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectOrderByUserId(int sessionUserId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Example example = new Example(AppUserOrder.class);
        example.createCriteria().andEqualTo("userId", sessionUserId);
        example.orderBy("creatTime").desc();
        List<AppUserOrder> orders = selectByExample(example);
        for (AppUserOrder order : orders) {
            Map<String, Object> map = new HashedMap();
            AppFind find = findService.selectByKey(order.getFindId());
            map.put("findName", find.getName());
            map.put("findId", find.getId());
            map.put("findNum", find.getFindId());
            map.put("findLess", cn.javayuan.app.utils.DateUtil.checkDate(find.getFindTime()));
            map.put("orderStateNum", order.getState());
            map.put("orderId", order.getId());
            map.put("findUserId", order.getFindUserId());
            map.put("orderUserId", order.getUserId());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 预约我的
     *
     * @param sessionUserId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectOrderMyByUserId(int sessionUserId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Example example = new Example(AppUserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("findUserId", sessionUserId);
        criteria.andNotEqualTo("state", "4");
        example.orderBy("creatTime").desc();
        List<AppUserOrder> orders = selectByExample(example);
        for (AppUserOrder order : orders) {
            Map<String, Object> map = new HashedMap();
            AppFind find = findService.selectByKey(order.getFindId());
            map.put("findName", find.getName());
            map.put("findId", find.getId());
            map.put("findNum", find.getFindId());
            map.put("findLess", cn.javayuan.app.utils.DateUtil.checkDate(find.getFindTime()));
            map.put("orderStateNum", order.getState());
            map.put("orderId", order.getId());
            map.put("findUserId", order.getFindUserId());
            map.put("orderUserId", order.getUserId());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 伪取消
     *
     * @param id
     * @return
     */
    @Override
    public boolean updateOrderState(int id, String state) {
        AppUserOrder order = selectByKey(id);
        order.setState(state);
        return updateAll(order);
    }

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<AppOrderDto> selectAllByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<AppUserOrder> orders=selectAll();
        List<AppOrderDto> orderDtos=new ArrayList<>();
        for(AppUserOrder order:orders){
            AppOrderDto orderDto=new AppOrderDto();
            orderDto.setOrder(order);
            AppUser fromeUser=userService.selectByKey(order.getUserId());
            orderDto.setFromeUser(fromeUser);
            AppUser toUser=userService.selectByKey(order.getFindUserId());
            orderDto.setToUser(toUser);
            AppFind find=findService.selectByKey(order.getFindId());
            orderDto.setFind(find);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }


}
