package com.casic.warehouse2.service;

import com.casic.warehouse2.bean.Goods;
import com.casic.warehouse2.bean.Provider;
import com.casic.warehouse2.common.ProviderQuery;

import java.util.List;

public interface ProviderService{
    /**
     * 通过id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 添加方法
     * @param record
     * @return
     */
    int insert(Provider record);

    /**
     * 修改供应商
     * @param record
     * @return
     */
    int updateByPrimaryKey(Provider record);

    /**
     * 查询供应商
     */
    List<Provider> getPageList(ProviderQuery providerQuery);

    /**
     * 供应商总数
     * @param providerQuery
     * @return
     */
    Long getCount(ProviderQuery providerQuery);

    Provider selectByPrimaryKey(Integer id);
    /**
     * 查询可用的供应商
     */
    List<Provider> selectAvailable();

    /**
     * 查询所有  未使用分页功能的
     * @param provider
     * @return
     */
    List<Provider> allProviderForList(Provider provider);
}
