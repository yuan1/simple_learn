package cn.javayuan.app.mapper;

import cn.javayuan.app.entity.AppUserValue;
import cn.javayuan.system.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppUserValueMapper extends MyMapper<AppUserValue> {
    List<String> selectValueDistinct(@Param("name") String name,@Param("userId") int userId);
    List<String> selectUserIdDistinct(@Param("name") String name,@Param("userValue") String userValue);
   AppUserValue selectTopCreate(@Param("name") String name,@Param("userId") int userId,@Param("userValue") String userValue );
   AppUserValue selectTopCreateRe(@Param("name") String name,@Param("userId") int userId,@Param("userValue") String userValue );
}