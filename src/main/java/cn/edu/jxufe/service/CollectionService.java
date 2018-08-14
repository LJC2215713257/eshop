package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Collectioninfo;

import java.util.List;

public interface CollectionService {
    List<Integer> findByPage(int mid);
}
