package com.changgou.service.goods.service;

import com.changgou.goods.pojo.Album;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    List<Album> findAll();

    Album findById(Long id);

    void delete(Long id);

    void add(Album album);

    void update(Album album);

    List<Album> findList(Map searchMap);

    Page<Album> findPage(int page, int size);

    Page<Album> findPage(Map searchMap, Integer page, Integer size);

}
