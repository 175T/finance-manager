package com.github.greatwqs.app.domain.dto;

import com.github.greatwqs.app.common.AppConstants;
import com.github.greatwqs.app.common.enums.OrderPriceType;
import com.github.greatwqs.app.domain.bo.OrderSearchBo;
import com.github.greatwqs.app.utils.PublicUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 订单查询页面数据封装
 *
 * @author greatwqs
 * Create on 2020/7/4
 */
@Getter
@Setter
@ToString
public class OrderSearchDto {

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
    private String subjectId;

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

    public OrderSearchDto(){
    }

    public OrderSearchDto(String createTimeStart, String createTimeEnd, String orderAccount, String ticketNo, String payType, String orderClass, String orderDes, String subjectId, Integer orderPriceType, Integer pageIndex) {
        this.createTimeStart = createTimeStart;
        this.createTimeEnd = createTimeEnd;
        this.orderAccount = orderAccount;
        this.ticketNo = ticketNo;
        this.payType = payType;
        this.orderClass = orderClass;
        this.orderDes = orderDes;
        this.subjectId = subjectId;
        this.orderPriceType = orderPriceType;
        this.pageIndex = pageIndex;
    }

    /***
     * 默认第一页 && 查询全部.
     *
     * @return
     */
    public static OrderSearchDto defaultDto() {
        OrderSearchDto searchDto = new OrderSearchDto();
        searchDto.setPageIndex(1);
        searchDto.setOrderPriceType(OrderPriceType.ALL.getType());
        return searchDto;
    }

    /**
     * 前台DTO 数据转换到 数据库查询 Bo
     *
     * @return
     */
    public OrderSearchBo toOrderSearchBo() {
        OrderSearchBo searchBo = new OrderSearchBo();
        if (StringUtils.isNotEmpty(this.createTimeStart)) {
            searchBo.setCreateTimeStart(this.createTimeStart);
        }
        if (StringUtils.isNotEmpty(this.createTimeEnd)) {
            searchBo.setCreateTimeEnd(this.createTimeEnd);
        }
        if (StringUtils.isNotEmpty(this.orderAccount)) {
            searchBo.setOrderAccount(this.orderAccount);
        }
        if (StringUtils.isNotEmpty(this.ticketNo)) {
            searchBo.setTicketNo(this.ticketNo);
        }
        if (StringUtils.isNotEmpty(this.payType)) {
            searchBo.setPayType(this.payType);
        }
        if (StringUtils.isNotEmpty(this.orderClass)) {
            searchBo.setOrderClass(this.orderClass);
        }
        if (StringUtils.isNotEmpty(this.orderDes)) {
            searchBo.setOrderDes(this.orderDes);
        }
        if (StringUtils.isNotEmpty(this.subjectId) && NumberUtils.isDigits(this.subjectId)) {
            searchBo.setSubjectId(Long.valueOf(this.subjectId));
        }
        searchBo.setOrderPriceType(OrderPriceType.of(this.orderPriceType).getType());
        searchBo.setBeginIndex(PublicUtils.getPageBeginIndex(this.pageIndex, AppConstants.ORDER_PAGE_SIZE));
        searchBo.setPageSize(AppConstants.ORDER_PAGE_SIZE);
        return searchBo;
    }
}