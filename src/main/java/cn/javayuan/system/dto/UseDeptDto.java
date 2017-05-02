/*
 * Copyright (c) 2016. 李明元 http://www.javayuan.cn.
 */

package cn.javayuan.system.dto;

import cn.javayuan.system.entity.SysDept;

/**
 * s使用的组织机构dto
 * Created by 李明元 on 2016/12/10.
 */
public class UseDeptDto {
    private String topDept;//上级组织机构名称
    private SysDept sysDept;

    public String getTopDept() {
        return topDept;
    }

    public void setTopDept(String topDept) {
        this.topDept = topDept;
    }

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }
}
