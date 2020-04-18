package com.casic.warehouse2.service.Impl;

import com.casic.warehouse2.bean.Provider;
import com.casic.warehouse2.common.GoodsQuery;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.casic.warehouse2.bean.Goods;
import com.casic.warehouse2.dao.GoodsMapper;
import com.casic.warehouse2.service.GoodsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Goods> getPageList(GoodsQuery goodsQuery) {
        return goodsMapper.getPageList(goodsQuery);
    }

    @Override
    public Long getCount(GoodsQuery goodsQuery) {
        return goodsMapper.getCount(goodsQuery);
    }

    @Override
    public List<Goods> allGoodsForList(Goods goods) {
        return goodsMapper.allGoodsForList(goods);
    }

}
