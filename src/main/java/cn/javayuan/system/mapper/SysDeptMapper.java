package cn.javayuan.system.mapper;

import cn.javayuan.system.entity.SysDept;
import cn.javayuan.system.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysDeptMapper extends MyMapper<SysDept> {
    SysDept selectDeptByUserId(@Param("id") int id);
}