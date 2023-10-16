package com.github.greatwqs.app.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * OrderSearchBo MyBatis 数据库查询专用. 对应于DTO为:
 *
 * @author greatwqs
 * Create on 2020/7/4
 * @see com.github.greatwqs.app.domain.dto.OrderSearchDto
 */
@Getter
@Setter
@ToString
public class OrderSearchBo {

    // 开始时间:
    private String createTimeStart;

    // 结束时间:
    private String createTimeEnd;

    // 缴费/收款人:
    private String orderAccount;

    // 票据号:
    private String ticketNo;

    // 收支方式:
    private String payType;

    // 班 级:
    private String orderClass;

    // 备 注:
    private String orderDes;

    // 项 目:
    private Long subjectId;

    /**
     * 收入, 支出, 不限
     * ALL(0), INCOME(1), OUTCOME(2);
     *
     * @see com.github.greatwqs.app.common.enums.OrderPriceType
     */
    private Integer orderPriceType;

    /***
     * 分页 LIMIT 参数
     */
    private Integer beginIndex;

    private Integer pageSize;
}