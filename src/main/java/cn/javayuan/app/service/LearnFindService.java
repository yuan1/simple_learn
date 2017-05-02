package cn.javayuan.app.service;

import cn.javayuan.app.dto.AppFindDto;
import cn.javayuan.app.entity.AppFind;
import cn.javayuan.app.entity.AppUser;
import cn.javayuan.system.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * appfind
 * Created by 李明元 on 2017/3/21.
 */
public interface LearnFindService extends BaseService<AppFind> {
    String createFind(AppUser user,String nowAppUserLocation);

    boolean updateFindLocationMap(String findId, String poiaddress, String map);
    boolean updateFindAddressMap(String findId, String poiaddress, String map);

    AppFind selectFindByFindId(String findId);

    boolean updateFindByFindId(AppFind find);

    boolean updateByFindId(AppFind find);

    List<Map<String, Object>> selectFindByType(HttpServletRequest request, String type, int state);
    List<Map<String, Object>> selectFindBySearch(HttpServletRequest request, String search);
    List<Map<String, Object>> selectUserFindByType(HttpServletRequest request, String type, String action);

    List<Map<String,Object>> selectFindByTypeToMap(HttpServletRequest request, String type);

    List<AppFindDto> selectByFind(AppFind find, int page, int rows);

    Map<String,Object> selectFindDataById(int id,HttpServletRequest request);

    boolean deleteFind(int id);
    boolean checkFind(int id);
    boolean checkFind2(int id);

    boolean updateFindState(int id, String s);

    boolean deleteFindAll(int id);

    AppFindDto selectFindDtoByKey(int id);
}
