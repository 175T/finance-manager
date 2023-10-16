package com.github.greatwqs.app.domain.po;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by MyBatis Generator 2020/06/28
* https://github.com/greatwqs/mybatis-generator-plugin
*/
@Getter
@Setter
@ToString
public class TicketTypePo {
    private Long id;

    private String name;

    private Boolean isvalid;

    private Long createuserid;

    private Date createtime;

    private Long updateuserid;

    private Date updatetime;
}