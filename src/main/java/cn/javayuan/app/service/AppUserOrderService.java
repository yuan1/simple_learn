package cn.javayuan.app.service;

import cn.javayuan.app.dto.AppOrderDto;
import cn.javayuan.app.entity.AppUserOrder;
import cn.javayuan.system.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 用户预约订单
 * Created by 李明元 on 2017/4/7.
 */
public interface AppUserOrderService extends BaseService<AppUserOrder> {
   String saveOrder(int findId, int sessionUserId);

   AppUserOrder selectOrderByOrderId(String orderId);
    Map<String, Object> selectOrder(int orderId);

    List<Map<String, Object>> selectOrderByUserId(int sessionUserId);
    List<Map<String, Object>> selectOrderMyByUserId(int sessionUserId);

    boolean updateOrderState(int id,String state);

    List<AppOrderDto> selectAllByPage(int page, int rows);
}
