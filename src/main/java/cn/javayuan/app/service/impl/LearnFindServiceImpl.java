package cn.javayuan.app.service.impl;

import cn.javayuan.app.dto.AppEvaDto;
import cn.javayuan.app.dto.AppFindDto;
import cn.javayuan.app.entity.*;
import cn.javayuan.app.mapper.AppUserEvaMapper;
import cn.javayuan.app.mapper.AppUserMapper;
import cn.javayuan.app.mapper.AppUserOrderMapper;
import cn.javayuan.app.mapper.AppUserStarMapper;
import cn.javayuan.app.service.*;
import cn.javayuan.app.utils.AppUtil;
import cn.javayuan.ucpass.utils.DateUtil;
import cn.javayuan.system.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * appfind
 * Created by 李明元 on 2017/3/21.
 */
@Service
public class LearnFindServiceImpl extends BaseServiceImpl<AppFind> implements LearnFindService {

    @Autowired
    private LearnUserService userService;

    @Autowired
    private AppUserStarService starService;

    @Autowired
    private AppUserOrderService orderService;
    @Autowired
    private AppUserEvaService evaService;

    /**
     * 保存并生成id
     *
     * @param user
     * @return
     */
    @Override
    public String createFind(AppUser user, String nowAppUserLocation) {
        String findId = DateUtil.dateToStr(new Date(), 5);
        AppFind find = new AppFind();
        find.setFindId(findId);
        find.setState("0");
        find.setLocationMap(nowAppUserLocation);
        find.setAppUserId(user.getId());
        if (save(find)) {
            return findId;
        }
        return null;
    }

    /**
     * 更新find我的位置
     *
     * @param findId
     * @param poiaddress
     * @param map
     * @return
     */
    @Override
    public boolean updateFindLocationMap(String findId, String poiaddress, String map) {
        AppFind find = selectFindByFindId(findId);
        if (find != null) {
            find.setLocationMap(map);
            find.setLocationName(poiaddress);
            return updateAll(find);
        }
        return false;
    }

    /**
     * 更新find地点
     *
     * @param findId
     * @param poiaddress
     * @param map
     * @return
     */
    @Override
    public boolean updateFindAddressMap(String findId, String poiaddress, String map) {
        AppFind find = selectFindByFindId(findId);
        if (find != null) {
            find.setAddressMap(map);
            find.setAddressName(poiaddress);
            return updateAll(find);
        }
        return false;
    }

    /**
     * 通过id查询find
     *
     * @param findId
     * @return
     */
    @Override
    public AppFind selectFindByFindId(String findId) {
        Example example = new Example(AppFind.class);
        example.createCriteria().andEqualTo("findId", findId);
        List<AppFind> finds = selectByExample(example);
        if (finds.size() > 0) {
            return finds.get(0);
        }
        return null;
    }

    /**
     * 通过gindid更新find
     *
     * @param find
     * @return
     */
    @Override
    public boolean updateFindByFindId(AppFind find) {
        Example example = new Example(AppFind.class);
        example.createCriteria().andEqualTo("findId", find.getFindId());
        AppFind findOld = mapper.selectByExample(example).get(0);
        findOld.setName(find.getName());
        findOld.setType(find.getType());
        findOld.setAttention(find.getAttention());
        findOld.setContent(find.getContent());
        findOld.setFindTime(find.getFindTime());
        findOld.setState(find.getState());
        findOld.setCreateTime(DateUtil.dateToStr(new Date()));
        return updateAll(findOld);
    }

    private void delByFindIdAndState(HttpServletRequest request) {
        Example example = new Example(AppFind.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("appUserId", AppUtil.getSessionUserId(request));
        criteria.andEqualTo("state", "0");
        mapper.deleteByExample(example);
    }

    /**
     * 更新
     *
     * @param find
     * @return
     */
    @Override
    public boolean updateByFindId(AppFind find) {
        Example example = new Example(AppFind.class);
        example.createCriteria().andEqualTo("findId", find.getFindId());
        int rs = mapper.updateByExample(find, example);
        if (rs > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取finds
     */
    @Override
    public List<Map<String, Object>> selectFindByType(HttpServletRequest request, String type, int state) {
        delByFindIdAndState(request);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (state == 0) {
            request.getSession().setAttribute("findTime" + type, DateUtil.dateToStr(new Date()));
        }
        List<AppFind> finds = new ArrayList<>();
        if (type.equals("1")) {
            Example example = new Example(AppFind.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("state", "1");
            if (state == 1) {
                String findTime = (String) request.getSession().getAttribute("findTime" + type);
                criteria.andGreaterThan("createTime", findTime);
            }
            criteria.andCondition("find_time > '" + DateUtil.dateToStr(new Date()) + "'");
            example.orderBy("createTime").asc();
            finds = selectByExample(example);
        } else {
            Example example = new Example(AppFind.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("type", type);
            criteria.andEqualTo("state", "1");
            if (state == 1) {
                String findTime = (String) request.getSession().getAttribute("findTime" + type);
                criteria.andGreaterThan("createTime", findTime);
            }
            criteria.andCondition("find_time > '" + DateUtil.dateToStr(new Date()) + "'");
            example.orderBy("createTime").asc();
            finds = selectByExample(example);
        }
        String userMap = AppUtil.getSessionAppUserLocation(request);
        if (userMap == null) {
            userMap = "116.331282,37.472906";
        }
        for (AppFind find : finds) {
            Map<String, Object> map = new HashMap<>();
            find.setGap((int) AppUtil.GetDistance(userMap, find.getLocationMap()));
            map.put("id", find.getId());
            map.put("name", find.getName());
            map.put("gap", find.getGap());
            map.put("content", find.getContent());
            map.put("findTime", find.getFindTime());
            map.put("createTime", find.getCreateTime());
            map.put("userId", find.getAppUserId());
            AppUser user = userService.selectByKey(find.getAppUserId());
            map.put("image", user.getImage());
            map.put("nickname", user.getNickname());
            map.put("less", cn.javayuan.app.utils.DateUtil.checkDate(find.getFindTime()));
            mapList.add(map);
        }

        return mapList;
    }
    /**
     * 搜索获取finds
     */
    @Override
    public List<Map<String, Object>> selectFindBySearch(HttpServletRequest request, String search) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<AppFind> finds = new ArrayList<>();
        Example example = new Example(AppFind.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("state", "1");
        criteria.andCondition("find_time > '" + DateUtil.dateToStr(new Date()) + "'");
        criteria.andLike("name","%"+search+"%");
        example.orderBy("createTime").asc();
        finds = selectByExample(example);
        String userMap = AppUtil.getSessionAppUserLocation(request);
        if (userMap == null) {
            userMap = "116.331282,37.472906";
        }
        for (AppFind find : finds) {
            Map<String, Object> map = new HashMap<>();
            find.setGap((int) AppUtil.GetDistance(userMap, find.getLocationMap()));
            map.put("id", find.getId());
            map.put("name", find.getName());
            map.put("gap", find.getGap());
            map.put("content", find.getContent());
            map.put("findTime", find.getFindTime());
            map.put("createTime", find.getCreateTime());
            map.put("userId", find.getAppUserId());
            AppUser user = userService.selectByKey(find.getAppUserId());
            map.put("image", user.getImage());
            map.put("nickname", user.getNickname());
            map.put("less", cn.javayuan.app.utils.DateUtil.checkDate(find.getFindTime()));
            mapList.add(map);
        }

        return mapList;
    }

    /**
     * 获取用户finds
     */
    @Override
    public List<Map<String, Object>> selectUserFindByType(HttpServletRequest request, String type, String action) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<AppFind> finds = new ArrayList<>();
        Example example = new Example(AppFind.class);
        Example.Criteria criteria = example.createCriteria();
        if (!type.equals("1")) {
            criteria.andEqualTo("type", type);
        }
        if (action.equals("find")) {
            criteria.andEqualTo("appUserId", AppUtil.getSessionAppUser(request).getId());
            criteria.andNotEqualTo("state", "3");
        }
        if (action.equals("star")) {
            List<Integer> integers = getUserStar(AppUtil.getSessionAppUser(request).getId());
            if (integers.size() > 0) {
                criteria.andIn("id", integers);
            } else {
                criteria.andCondition("id in ('')");
            }
        }
        criteria.andNotEqualTo("state", "0");
        example.orderBy("createTime").asc();
        finds = selectByExample(example);
        String userMap = AppUtil.getSessionAppUserLocation(request);
        if (userMap == null) {
            userMap = "116.331282,37.472906";
        }
        for (AppFind find : finds) {
            Map<String, Object> map = new HashMap<>();
            find.setGap((int) AppUtil.GetDistance(userMap, find.getLocationMap()));
            map.put("id", find.getId());
            map.put("name", find.getName());
            map.put("gap", find.getGap());
            map.put("content", find.getContent());
            map.put("findTime", find.getFindTime());
            map.put("createTime", find.getCreateTime());
            map.put("userId", find.getAppUserId());
            AppUser user = userService.selectByKey(find.getAppUserId());
            map.put("image", user.getImage());
            map.put("nickname", user.getNickname());
            map.put("less", cn.javayuan.app.utils.DateUtil.checkDate(find.getFindTime()));
            mapList.add(map);
        }

        return mapList;
    }

    private List<Integer> getUserStar(int userId) {
        List<Integer> integers = new ArrayList<>();
        Example example1 = new Example(AppUserStar.class);
        example1.createCriteria().andEqualTo("userId", userId);
        List<AppUserStar> stars = starService.selectByExample(example1);
        for (AppUserStar star : stars) {
            integers.add(star.getFindId());
        }
        return integers;
    }

    /**
     * 获取finds
     *
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> selectFindByTypeToMap(HttpServletRequest request, String type) {
        delByFindIdAndState(request);
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<AppFind> finds = new ArrayList<>();
        if (type.equals("1")) {
            Example example = new Example(AppFind.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("state", "1");
            criteria.andCondition("find_time > '" + DateUtil.dateToStr(new Date()) + "'");
            finds = selectByExample(example);
        } else {
            Example example = new Example(AppFind.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("type", type);
            criteria.andEqualTo("state", "1");
            criteria.andCondition("find_time > '" + DateUtil.dateToStr(new Date()) + "'");
            finds = selectByExample(example);
        }
        for (AppFind find : finds) {
            Map<String, Object> map = new HashMap<>();
            AppUser user = userService.selectByKey(find.getAppUserId());
            map.put("id", find.getId());
            map.put("content", find.getContent());
            map.put("name", find.getName());
            map.put("map", find.getLocationMap());
            map.put("userId", user.getId());
            map.put("nickname", user.getNickname());
            map.put("image", user.getImage());
            map.put("time", find.getFindTime());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 条件查询
     *
     * @param find
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<AppFindDto> selectByFind(AppFind find, int page, int rows) {
        Example example = new Example(AppFind.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(find.getName())) {
            criteria.andLike("name", "%" + find.getName() + "%");
        }
        if (StringUtil.isNotEmpty(find.getType())) {
            criteria.andEqualTo("type", find.getType());
        }
        if (StringUtil.isNotEmpty(find.getState())) {
            criteria.andEqualTo("state", find.getState());
        }
        if (StringUtil.isNotEmpty(find.getCreateTime())) {
            criteria.andLike("createTime", "%" + find.getCreateTime() + "%");
        }
        criteria.andNotEqualTo("state", "0");
        example.orderBy("type");
        example.orderBy("createTime");
        PageHelper.startPage(page, rows);
        List<AppFind> finds=selectByExample(example);
        List<AppFindDto> findDtos=new ArrayList<>();
        for(AppFind find1:finds){
            AppFindDto findDto=selectFindDtoByKey(find1.getId());
            findDtos.add(findDto);
        }

        return findDtos ;
    }

    /**
     * 通过id获取find的信息
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> selectFindDataById(int id, HttpServletRequest request) {
        AppFind find = selectByKey(id);
        AppUser user = userService.selectByKey(find.getAppUserId());
        Map<String, Object> map = new HashedMap();
        map.put("userId", user.getId());
        map.put("findId", find.getId());
        map.put("findNum", find.getFindId());
        map.put("userImage", user.getImage());
        map.put("userNick", user.getNickname());
        map.put("userName", user.getUsername());
        map.put("findAddr", find.getAddressName());
        map.put("findCont", find.getContent());
        map.put("findLess", cn.javayuan.app.utils.DateUtil.checkDate(find.getFindTime()));
        map.put("findTime", find.getFindTime());
        map.put("findAtte", find.getAttention());
        map.put("findName", find.getName());
        map.put("star", selectStar(AppUtil.getSessionUserId(request), find.getId()));
        map.put("order", selectOrder(AppUtil.getSessionUserId(request), find.getId()));
        map.put("edit", find.getAppUserId() - AppUtil.getSessionUserId(request));
        List<Map<String, Object>> mapList = evaService.selectAllEvaByFindId(find.getId());
        map.put("evas", mapList);
        map.put("evaSize", mapList.size());
        map.put("evaGoodSize", evaService.selectTypeSizeByFindId(find.getId(), "1"));
        map.put("evaBadSize", evaService.selectTypeSizeByFindId(find.getId(), "0"));
        return map;
    }

    /**
     * 伪删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteFind(int id) {
        AppFind find = selectByKey(id);
        find.setState("3");
        return updateAll(find);
    }

    /**
     * 检查是否删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean checkFind(int id) {
        AppFind find = selectByKey(id);
        return find.getState().equals("3");
    }

    /**
     * 检查是否禁止
     *
     * @param id
     * @return
     */
    @Override
    public boolean checkFind2(int id) {
        AppFind find = selectByKey(id);
        return find.getState().equals("2");
    }

    /**
     * 启用 停用
     *
     * @param id
     * @param s
     * @return
     */
    @Override
    public boolean updateFindState(int id, String s) {
        AppFind find = selectByKey(id);
        find.setState(s);
        return updateAll(find);
    }

    /**
     * 全部删除
     * @param id
     * @return
     */
    @Override
    public boolean deleteFindAll(int id) {
        Example example = new Example(AppUserEva.class);
        example.createCriteria().andEqualTo("findId",id);
        evaService.deleteByExample(example);
        Example example1=new Example(AppUserOrder.class);
        example1.createCriteria().andEqualTo("findId",id);
        orderService.deleteByExample(example);
        Example example2=new Example(AppUserStar.class);
        example2.createCriteria().andEqualTo("findId",id);
        starService.deleteByExample(example);
        return delete(id);
    }

    @Override
    public AppFindDto selectFindDtoByKey(int id) {
        AppFind find=selectByKey(id);
        AppFindDto findDto=new AppFindDto();
        findDto.setFind(find);
        AppUser user=userService.selectByKey(find.getAppUserId());
        findDto.setUser(user);
        List<AppUserEva> evas=evaService.selectAllByFindId(find.getId());
        List<AppEvaDto> evaDtos=new ArrayList<>();
        for(AppUserEva eva:evas){
            AppEvaDto evaDto=evaService.selectEvaDtoById(eva.getId());
            evaDtos.add(evaDto);
        }
        findDto.setEvas(evaDtos);
        return findDto;
    }

    /**
     * 查询是否存在收藏
     *
     * @param userId
     * @param findId
     * @return
     */
    private int selectStar(int userId, int findId) {
        Example example = new Example(AppUserStar.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("findId", findId);
        return starService.selectByExample(example).size();
    }

    /**
     * 查询是否存在order
     *
     * @param userId
     * @param findId
     * @return
     */
    private int selectOrder(int userId, int findId) {
        Example example = new Example(AppUserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("findId", findId);
        criteria.andNotEqualTo("state", "4");
        criteria.andNotEqualTo("state", "3");
        criteria.andNotEqualTo("state", "6");
        criteria.andNotEqualTo("state", "5");
        return orderService.selectByExample(example).size();
    }
}
