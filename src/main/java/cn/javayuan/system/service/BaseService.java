/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.service;

import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 通用接口
 * Created by 李明元 on 2016/11/30.
 */
@Service
public interface BaseService<T> {

    T selectByKey(Object key);

    boolean save(T entity);

    boolean delete(Object key);

    boolean updateAll(T entity);

    boolean updateNotNull(T entity);

    List<T> selectByExample(Object example);

    List<T> selectAll();

    boolean deleteByExample(Example example);

    //TODO 其他...
}