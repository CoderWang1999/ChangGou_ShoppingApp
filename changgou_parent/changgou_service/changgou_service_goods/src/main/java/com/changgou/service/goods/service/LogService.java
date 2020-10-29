package com.changgou.service.goods.service;

import com.changgou.goods.pojo.Log;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface LogService {
    List<Log> findAll();

    Log findById(Long id);

    void add(Log log);

    void update(Log log);

    void delete(Long id);

    List<Log> findList(Map searchMap);

    Page<Log> findPage(int page, int size);

    Page<Log> findPage(Map searchMap, int page, int size);
}
