package cn.javayuan.system.mapper;

import cn.javayuan.system.entity.SysUser;
import cn.javayuan.system.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends MyMapper<SysUser> {

    int deleteUserByRoleIds(@Param("roleid") int roleid);

    int deleteUserByDeptIds(@Param("deptid")int deptid);

    List<SysUser> selectSysUserByDeptId(@Param("deptid")int deptid);


}