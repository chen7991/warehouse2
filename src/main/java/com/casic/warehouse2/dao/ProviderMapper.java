package com.casic.warehouse2.dao;

import com.casic.warehouse2.bean.Provider;
import com.casic.warehouse2.common.ProviderQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProviderMapper {
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

    /**
     * 通过id查询供应商
     * @param id
     * @return
     */
    Provider selectByPrimaryKey(Integer id);
    /**
     * 查询可用的供应商
     */
    List<Provider> selectAvailable();

    /**
     * 查询所有未使用分页功能的
     * @param provider
     * @return
     */
    List<Provider> allProviderForList(Provider provider);
}