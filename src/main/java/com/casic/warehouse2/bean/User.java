package com.casic.warehouse2.bean;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;

    private String name;

    private String loginname;

    private String address;

    private int sex;

    private String remark;

    private Long deptid;

    private Date hiredate;

    private Long mgr;

    private Long available;

    private Long ordernum;

    private Long type;

    private String imgpath;

    private String salt;

}
