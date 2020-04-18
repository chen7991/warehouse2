package com.casic.warehouse2.service.Impl;

import com.casic.warehouse2.common.ProviderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.casic.warehouse2.dao.ProviderMapper;
import com.casic.warehouse2.bean.Provider;
import com.casic.warehouse2.service.ProviderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService{

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return providerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Provider record) {
        return providerMapper.insert(record);
    }


    @Override
    public int updateByPrimaryKey(Provider record) {
        return providerMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Provider> getPageList(ProviderQuery providerQuery) {
        return providerMapper.getPageList(providerQuery);
    }

    @Override
    public Long getCount(ProviderQuery providerQuery) {
        return providerMapper.getCount(providerQuery);
    }

    @Override
    public Provider selectByPrimaryKey(Integer id) {
        return providerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Provider> selectAvailable() {
        return providerMapper.selectAvailable();
    }

    @Override
    public List<Provider> allProviderForList(Provider provider) {
        return providerMapper.allProviderForList(provider);
    }


}
