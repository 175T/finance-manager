package com.github.greatwqs.app.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 订单查询页面数据封装, 查询参数回传给JSP页面, 用来翻页时自动带上查询的参数!
 *
 * @author greatwqs
 * Create on 2020/7/5
 */
@Getter
@Setter
@ToString
public class OrderQueryParamsVo {

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
    private Integer pageIndex;

    public OrderQueryParamsVo() {
    }

    public OrderQueryParamsVo(
            String createTimeStart, String createTimeEnd, String orderAccount,
            String ticketNo, String payType, String orderClass, String orderDes,
            String subjectId, Integer orderPriceType, Integer pageIndex
    ) {
        this.createTimeStart = StringUtils.trimToEmpty(createTimeStart);
        this.createTimeEnd = StringUtils.trimToEmpty(createTimeEnd);
        this.orderAccount = StringUtils.trimToEmpty(orderAccount);
        this.ticketNo = StringUtils.trimToEmpty(ticketNo);
        this.payType = StringUtils.trimToEmpty(payType);
        this.orderClass = StringUtils.trimToEmpty(orderClass);
        this.orderDes = StringUtils.trimToEmpty(orderDes);
        this.subjectId = StringUtils.isNotEmpty(subjectId) && NumberUtils.isDigits(subjectId) ? Long.valueOf(subjectId) : 0;
        this.orderPriceType = orderPriceType;
        this.pageIndex = pageIndex;
    }
}