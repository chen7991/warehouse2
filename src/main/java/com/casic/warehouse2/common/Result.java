package com.casic.warehouse2.common;

import lombok.Data;

/**
 * 统一返回类
 */
@Data
public class Result {
    private int code;
    private String msg;
    private Long count;
    private Object data;

}
