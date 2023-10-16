package com.github.greatwqs.app.domain.po;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by MyBatis Generator 2020/07/04
 * https://github.com/greatwqs/mybatis-generator-plugin
 */
@Getter
@Setter
@ToString
public class OrderPo {
    private Long id;

    private String orderaccount;

    private String orderclass;

    private Long subjectid;

    private Long tickettypeid;

    private String ticketno;

    private BigDecimal orderprice;

    private String paytype;

    private Date ordertime;

    private String orderdes;

    private Boolean isvalid;

    private Long createuserid;

    private Date createtime;

    private Long updateuserid;

    private Date updatetime;
}