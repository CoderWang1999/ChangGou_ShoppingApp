package com.changgou.service.goods.service.impl;

import com.changgou.goods.pojo.Log;
import com.changgou.service.goods.dao.LogMapper;
import com.changgou.service.goods.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Log> findAll() {
        return logMapper.selectAll();
    }

    @Override
    public Log findById(Long id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Log log) {
        logMapper.insert(log);
    }

    @Override
    public void update(Log log) {
        logMapper.updateByPrimaryKey(log);
    }

    @Override
    public void delete(Long id) {
        logMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Log> findList(Map searchMap) {
        Example example = createExample(searchMap);
        return logMapper.selectByExample(example);
    }

    @Override
    public Page<Log> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<Log>) logMapper.selectAll();
    }

    @Override
    public Page<Log> findPage(Map searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<Log>) logMapper.selectByExample(example);
    }


    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Log.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // xid
            if (searchMap.get("xid") != null && !"".equals(searchMap.get("xid"))) {
                criteria.andLike("xid", "%" + searchMap.get("xid") + "%");
            }
            // ext
            if (searchMap.get("ext") != null && !"".equals(searchMap.get("ext"))) {
                criteria.andLike("ext", "%" + searchMap.get("ext") + "%");
            }
            // branchId
            if (searchMap.get("branchId") != null) {
                criteria.andEqualTo("branchId", searchMap.get("branchId"));
            }
            // logStatus
            if (searchMap.get("logStatus") != null) {
                criteria.andEqualTo("logStatus", searchMap.get("logStatus"));
            }
            // logCreated
            if (searchMap.get("logCreated") != null) {
                criteria.andEqualTo("logCreated", searchMap.get("logCreated"));
            }
            // logModified
            if (searchMap.get("logModified") != null) {
                criteria.andEqualTo("logModified", searchMap.get("logModified"));
            }
        }
        return example;
    }
}
