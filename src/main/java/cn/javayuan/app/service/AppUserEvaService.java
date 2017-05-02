package cn.javayuan.app.service;

import cn.javayuan.app.dto.AppEvaDto;
import cn.javayuan.app.entity.AppUserEva;
import cn.javayuan.system.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;
/**
 * 评价
 * Created by 李明元 on 2017/4/9.
 */
public interface AppUserEvaService extends BaseService<AppUserEva> {
    boolean saveEva(String content, String type, int orderId, HttpServletRequest request);

    boolean checkEva(int orderId);

    List<Map<String,Object>> selectAllEvaByFindId(int id);


    int selectTypeSizeByFindId(int findId,String type);

    List<AppEvaDto> selectAllByPage(int page, int rows);
    List<AppUserEva> selectAllByFindId(int findId);

    boolean updateState(int id, String s);

    AppEvaDto selectEvaDtoById(int id);
}
