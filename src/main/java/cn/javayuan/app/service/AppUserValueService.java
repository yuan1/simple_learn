package cn.javayuan.app.service;

import cn.javayuan.app.entity.ApiConfig;
import cn.javayuan.app.entity.AppUserValue;
import cn.javayuan.system.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * app user扩展属性
 * Created by 李明元 on 2017/3/19.
 */

public interface AppUserValueService extends BaseService<AppUserValue> {
    AppUserValue selectValueByUserId(int userId,String name);

    boolean saveValue(String value, int id, String name);

    List<Map<String,Object>> selectChatList(HttpServletRequest request);
    boolean saveFeedback(HttpServletRequest request, String feedback);
}
