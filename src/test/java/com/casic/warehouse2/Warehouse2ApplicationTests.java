package com.casic.warehouse2;


import com.casic.warehouse2.bean.Customer;
import com.casic.warehouse2.common.CustomerQuery;
import com.casic.warehouse2.dao.CustomerDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
class Warehouse2ApplicationTests {


    @Autowired
    CustomerDao customerDao;

    @Test
    void contextLoads() {
    }

}

