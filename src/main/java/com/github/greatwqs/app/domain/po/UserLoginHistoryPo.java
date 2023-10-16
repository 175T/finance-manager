package com.github.greatwqs.app.domain.po;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by MyBatis Generator 2020/06/27
* https://github.com/greatwqs/mybatis-generator-plugin
*/
@Getter
@Setter
@ToString
public class UserLoginHistoryPo {
    private Long id;

    private Long userid;

    private String loginip;

    private String logintoken;

    private Date expiretime;

    private Boolean isvalid;

    private Date createtime;

    private Date updatetime;
}