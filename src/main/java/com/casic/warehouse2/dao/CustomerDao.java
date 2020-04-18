package com.casic.warehouse2.dao;

import com.casic.warehouse2.bean.Customer;
import com.casic.warehouse2.common.CustomerQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface CustomerDao {

    public List<Customer> getPageList(CustomerQuery query);

    public Long getCount(CustomerQuery query);

    boolean addCustomer(Customer customer);

    Customer getCustomer(@Param("id") String id);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomerByIds( List<Long> ids);

    /**
     * 查询客户数据未使用分页的
     * @param customer
     * @return
     */
    List<Customer> allCustomerForList(Customer customer);
}
