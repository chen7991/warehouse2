package com.casic.warehouse2.common;

import lombok.Data;

@Data
public class GoodsQuery {

    private Long page;
    private Long limit;
    private String providerid;
    private String goodsname;
    private String productcode;
    private String promitcode;
    private String description;
    private String size;
}
