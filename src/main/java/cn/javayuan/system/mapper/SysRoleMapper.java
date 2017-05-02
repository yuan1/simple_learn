package cn.javayuan.system.mapper;

import cn.javayuan.system.entity.SysRole;
import cn.javayuan.system.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper extends MyMapper<SysRole> {
    SysRole selectRoleByUserId(@Param("id") int id);
}