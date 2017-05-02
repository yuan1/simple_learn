package cn.javayuan.system.mapper;

import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper extends MyMapper<SysPermission> {
    List<SysPermission> selectMenuListByUserId(@Param("id") int id);

    List<SysPermission> selectPermissionListByUserId(@Param("id") int id);

    List<SysPermission> selectPermissionListByUserIdAndRoleId(@Param("parentid") int parentid,@Param("roleid") int roleid);

    List<SysPermission>selectTopMenuByUserRole(@Param("userid") int userid);

    List<SysPermission>selectMenuByUserRole(@Param("userid") int userid);

    List<SysPermission>selectNotTopMenuByUserRole(@Param("userid") int userid);

    List<SysPermission> selectNotTopMenuByUserRoleAndTopMenu(@Param("userid") int userid, @Param("menuid") int menuid);

    List<SysPermission> selectPermissionByRoleId(@Param("roleid") int roleid);

}