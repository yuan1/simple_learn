/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.service;

import cn.javayuan.system.dto.UseDeptDto;
import cn.javayuan.system.entity.SysDept;

import java.util.List;

/**
 * 组织机构业务接口
 * Created by 李明元 on 2016/12/9.
 */
public interface SysDeptService extends BaseService<SysDept>{
    //获取组织机构ztree
    String getDeptZtreeJson();

    //通过用户id获取组织机构
    SysDept findDeptByUserId(int id);

    //获取用户的全部组织机构
    List<SysDept> findDeptListByUser();
    //删除组织机构
    boolean deleteDeptById(int id);

    UseDeptDto findDeptById(int id);
}
