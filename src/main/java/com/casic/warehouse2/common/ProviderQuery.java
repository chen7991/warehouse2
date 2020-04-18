package com.casic.warehouse2.common;

import lombok.Data;

@Data
public class ProviderQuery {

    private Long page;
    private Long limit;
    private String providername;
    private String phone;
    private String connectionperson;

}
