package com.casic.warehouse2.dao;

import com.casic.warehouse2.bean.Goods;
import com.casic.warehouse2.bean.Provider;
import com.casic.warehouse2.common.GoodsQuery;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> getPageList(GoodsQuery goodsQuery);

    Long getCount(GoodsQuery goodsQuery);

    /**
     * 使用未分页的方法查询所有
     * @param goods
     * @return
     */
    List<Goods> allGoodsForList(Goods goods);
}