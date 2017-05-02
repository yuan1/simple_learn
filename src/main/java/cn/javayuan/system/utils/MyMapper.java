/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.utils;

/**
 * 继承自己的MyMapper
 * Created by 李明元 on 2016/11/30.
 */
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
