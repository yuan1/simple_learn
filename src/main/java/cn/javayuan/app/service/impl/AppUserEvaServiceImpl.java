package cn.javayuan.app.service.impl;

import cn.javayuan.app.dto.AppEvaDto;
import cn.javayuan.app.entity.AppFind;
import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.entity.AppUserEva;
import cn.javayuan.app.entity.AppUserOrder;
import cn.javayuan.app.service.AppUserEvaService;
import cn.javayuan.app.service.AppUserOrderService;
import cn.javayuan.app.service.LearnFindService;
import cn.javayuan.app.service.LearnUserService;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
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
 * 评价
 * Created by 李明元 on 2017/4/9.
 */
@Service
public class AppUserEvaServiceImpl extends BaseServiceImpl<AppUserEva> implements AppUserEvaService {
    @Autowired
    private AppUserOrderService orderService;

    @Autowired
    private LearnFindService findService;

    @Autowired
    private LearnUserService userService;

    /**
     * 保存评价
     * @param content
     * @param type
     * @param orderId
     * @param request
     * @return
     */
    @Override
    public boolean saveEva(String content, String type, int orderId, HttpServletRequest request) {
        AppUser user= AppUtil.getSessionAppUser(request);
        AppUserOrder order =orderService.selectByKey(orderId);
        AppFind find=findService.selectByKey(order.getFindId());
        AppUserEva eva=new AppUserEva();
        eva.setContent(content);
        eva.setCreateTime(cn.javayuan.ucpass.utils.DateUtil.dateToStr(new Date()));
        eva.setFindId(find.getId());
        eva.setUseriId(user.getId());
        eva.setType(type);
        eva.setOrderId(orderId);
        eva.setState("0");
        orderService.updateOrderState(orderId,"6");
        return save(eva);
    }

    /**
     * 检查是否已经评价
     * @param orderId
     * @return
     */
    @Override
    public boolean checkEva(int orderId) {
        Example example = new Example(AppUserEva.class);
        example.createCriteria().andEqualTo("orderId",orderId);
        return selectByExample(example).size()>0;
    }

    /**
     * 查询全部评价
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAllEvaByFindId(int id) {
        List<Map<String, Object>> mapList=new ArrayList<>();
        Example example = new Example(AppUserEva.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("findId",id);
        criteria.andEqualTo("state","0");
        List<AppUserEva> evas=selectByExample(example);
        for(AppUserEva eva:evas){
            Map<String,Object> map= new HashedMap();
            AppUser user=userService.selectByKey(eva.getUseriId());
            map.put("userNick",user.getNickname());
            map.put("userImage",user.getImage());
            map.put("evaContent",eva.getContent());
            map.put("evaType",checkEvaType(eva.getType()));
            map.put("evaTime",eva.getCreateTime());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 查询评价数
     * @param findId
     * @param type
     * @return
     */
    @Override
    public int selectTypeSizeByFindId(int findId, String type) {
        Example example = new Example(AppUserEva.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("findId",findId);
        criteria.andEqualTo("type",type);
        criteria.andEqualTo("state","0");
        return selectByExample(example).size();
    }

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<AppEvaDto> selectAllByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<AppUserEva> evas = selectAll();
        List<AppEvaDto> evaDtos=new ArrayList<>();
        for(AppUserEva eva:evas){
            AppFind find=findService.selectByKey(eva.getFindId());
            AppEvaDto evaDto=new AppEvaDto();
            evaDto.setFind(find);
            AppUser user=userService.selectByKey(eva.getUseriId());
            evaDto.setUser(user);
            evaDto.setEva(eva);
            evaDtos.add(evaDto);
        }
        return evaDtos;
    }

    /**
     * 通过findid查询评价
     * @param findId
     * @return
     */
    @Override
    public List<AppUserEva> selectAllByFindId(int findId) {
        Example example = new Example(AppUserEva.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("findId",findId);
        criteria.andEqualTo("state","0");
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
        AppUserEva eva=selectByKey(id);
        eva.setState(s);
        return updateAll(eva);
    }

    @Override
    public AppEvaDto selectEvaDtoById(int id) {
        AppEvaDto evaDto=new AppEvaDto();
        AppUserEva eva=selectByKey(id);
        evaDto.setEva(eva);
        AppUser user=userService.selectByKey(eva.getUseriId());
        evaDto.setUser(user);
        AppFind fin=findService.selectByKey(eva.getFindId());
        evaDto.setFind(fin);
        return evaDto;
    }

    private String checkEvaType(String type){
        if(type.equals("0")){
            return "差评";
        }else if(type.equals("1")){
            return "好评";
        }
        return "未评";
    }

}
