package com.casic.warehouse2.common;

import lombok.Data;

@Data
public class CustomerQuery {

    private Long page;
    private Long limit;
    private String customername;
    private String telephone;
    private String connectionperson;
}
