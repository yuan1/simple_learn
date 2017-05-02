/*
 * Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
 */

package cn.javayuan.system.service.impl;

import cn.javayuan.system.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基础业务实现类
 * Created by 李明元 on 2016/11/30.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    public static Logger LOG=Logger.getLogger(BaseService.class);
    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public boolean save(T entity) {
        return mapper.insert(entity) > 0;
    }

    public boolean delete(Object key) {
        return mapper.deleteByPrimaryKey(key) > 0;
    }

    public boolean updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity) > 0;
    }

    public boolean updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity) > 0;
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public boolean deleteByExample(Example example){
        return mapper.deleteByExample(example)>0;
    }
}
