package com.casic.warehouse2.service;


import com.casic.warehouse2.bean.Customer;
import com.casic.warehouse2.common.CustomerQuery;
import com.casic.warehouse2.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> getPageList( CustomerQuery customerQuery){
        return customerDao.getPageList(customerQuery);
    }
    public Long getCount(CustomerQuery query){
        return customerDao.getCount(query);
    }

    public boolean addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    public Customer getCustomer(String id) {
        return customerDao.getCustomer(id);
    }

    public boolean updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    public boolean deleteCustomerByIds(List<Long> ids) {
        return customerDao.deleteCustomerByIds(ids);
    }

    /**
     * 查询客户数据未使用分页的
     * @param customer
     * @return
     */
    public List<Customer> allCustomerForList(Customer customer) {
        return customerDao.allCustomerForList(customer);
    }
}
