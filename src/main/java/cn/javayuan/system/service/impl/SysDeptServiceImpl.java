/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.service.impl;

import cn.javayuan.system.dto.UseDeptDto;
import cn.javayuan.system.entity.SysDept;
import cn.javayuan.system.entity.SysUserDept;
import cn.javayuan.system.mapper.SysDeptMapper;
import cn.javayuan.system.mapper.SysUserDeptMapper;
import cn.javayuan.system.mapper.SysUserMapper;
import cn.javayuan.system.service.SysDeptService;
import cn.javayuan.system.utils.ShiroUtil;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织机构业务实现类
 * Created by 李明元 on 2016/12/9.
 */
@Service
public class SysDeptServiceImpl extends  BaseServiceImpl<SysDept> implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private  SysUserDeptMapper sysUserDeptMapper;

    /**
     * 获取组织机构树
     * @return
     */
    @Override
    public String getDeptZtreeJson() {
        List<SysDept> deptList = new ArrayList<SysDept>();
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        deptList = findDeptByUser();
        for (SysDept dept : deptList) {
            Map<String, String> stringStringMap = new LinkedHashMap<String, String>();
            stringStringMap.put("id", String.valueOf(dept.getId()));
            stringStringMap.put("pId", String.valueOf(dept.getParentid()));
            stringStringMap.put("name", dept.getName());
            if (dept.getParentid() == 0 ||findDeptByParentId(dept.getId()).size()>0) {
                stringStringMap.put("open", "true");
                stringStringMap.put("iconOpen","lib/zTree/v3/css/zTreeStyle/img/diy/1_open.png");
                stringStringMap.put("iconClose","lib/zTree/v3/css/zTreeStyle/img/diy/1_close.png");
            } else {
                stringStringMap.put("icon", "lib/zTree/v3/css/zTreeStyle/img/diy/2.png");
            }
            mapList.add(stringStringMap);
        }
        return JSONUtils.toJSONString(mapList);
    }

    /**
     * 通过用户id获取组织机构
     *
     * @param id
     * @return
     */
    @Override
    public SysDept findDeptByUserId(int id) {
        Example example = new Example(SysUserDept.class);
        example.createCriteria().andEqualTo("sysUserId", id);
        return sysDeptMapper.selectByPrimaryKey(sysUserDeptMapper.selectByExample(example).get(0).getSysDeptId());
    }

    /**
     * 获取用户的全部组织机构
     * @return
     */
    @Override
    public List<SysDept> findDeptListByUser() {
        Example example = new Example(SysDept.class);
        example.createCriteria().andEqualTo("parentid",ShiroUtil.getSessionActiveUser().getSysDept().getId());
        example.orderBy("sort");
        List<SysDept> sysDepts =sysDeptMapper.selectByExample(example);
        sysDepts.add(ShiroUtil.getSessionActiveUser().getSysDept());
        return sysDepts;
    }

    /**
     * 删除组织机构
     * 删除组织机构 同时删除组织机构下的用户
     * @param id
     * @return
     */
    @Override
    public boolean deleteDeptById(int id) {
        try{
            deleteDeptByIdTo(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UseDeptDto findDeptById(int id) {
        UseDeptDto useDeptDto = new UseDeptDto();
        SysDept dept = sysDeptMapper.selectByPrimaryKey(id);
        useDeptDto.setSysDept(dept);
        if(dept.getParentid()!=0){
            useDeptDto.setTopDept(sysDeptMapper.selectByPrimaryKey(dept.getParentid()).getName());
        }
        return useDeptDto;
    }

    /**
     * 递归删除组织机构及用户
     * @param id
     */
    private void deleteDeptByIdTo(int id){
        sysUserMapper.deleteUserByDeptIds(id);
        Example example = new Example(SysUserDept.class);
        example.createCriteria().andEqualTo("sysDeptId",id);
        sysUserDeptMapper.deleteByExample(example);
        sysDeptMapper.deleteByPrimaryKey(id);
        Example example1= new Example(SysDept.class);
        example1.createCriteria().andEqualTo("parentid",id);
        List<SysDept> sysDepts=sysDeptMapper.selectByExample(example1);
        if(sysDepts!=null&&sysDepts.size()>0){
            for(SysDept dept:sysDepts){
                deleteDeptByIdTo(dept.getId());
            }
        }
    }
    /**
     * 获取用户对应的全部组织和子组织
     * @return
     */
    private List<SysDept> findDeptByUser() {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(ShiroUtil.getSessionActiveUser().getSysDept().getId());
        List<Integer> integerList = getAllDeptChildren(dept.getId());
        List<SysDept> deptList = new ArrayList<SysDept>();
        for (int ii : integerList) {
            SysDept dept1 = sysDeptMapper.selectByPrimaryKey(ii);
            deptList.add(dept1);
        }
        return deptList;

    }
    /**
     * 存放获取到的组织机构子节点
     */
    private List<Integer> deptList = null;

    /**
     * 获取组织机构子节点
     * @param pid
     * @return
     */
    private List<Integer> getAllDeptChildren(int pid) {
        deptList = new ArrayList<Integer>();
        deptList.add(pid);
        return getDeptChildren(pid);
    }

    /**
     * 获取组织机构子节点
     * @param pid
     * @return
     */
    private List<Integer> getDeptChildren(int pid) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(pid);
        if (findDeptByParentId(dept.getId()).size() > 0) {
            for (SysDept dept2 : findDeptByParentId(dept.getId())) {
                deptList.add(dept2.getId());
                getDeptChildren(dept2.getId());
            }
        }
        return deptList;
    }

    /**
     * 通过parentid获取组织机构
     * @param id
     * @return
     */
    private List<SysDept> findDeptByParentId(int id){
        Example example = new Example(SysDept.class);
        example.createCriteria().andEqualTo("parentid",id);
        return sysDeptMapper.selectByExample(example);
    }

}
