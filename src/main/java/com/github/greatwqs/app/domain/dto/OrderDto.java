package com.github.greatwqs.app.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 订单创建 | 订单更新
 *
 * @author greatwqs
 * Create on 2020/7/4
 */
@Getter
@Setter
@ToString
public class OrderDto {
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

    private Long createuserid;

    private Long updateuserid;

    public OrderDto(){
    }

    public OrderDto(Long id, String orderaccount, String orderclass, Long subjectid,
                    Long tickettypeid, String ticketno, BigDecimal orderprice, String paytype,
                    Date ordertime, String orderdes, Long createuserid, Long updateuserid
    ) {
        this.id = id;
        this.orderaccount = orderaccount;
        this.orderclass = orderclass;
        this.subjectid = subjectid;
        this.tickettypeid = tickettypeid;
        this.ticketno = ticketno;
        this.orderprice = orderprice;
        this.paytype = paytype;
        this.ordertime = ordertime;
        this.orderdes = orderdes;
        this.createuserid = createuserid;
        this.updateuserid = updateuserid;
    }
}