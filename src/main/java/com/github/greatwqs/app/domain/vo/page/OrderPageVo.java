package com.github.greatwqs.app.domain.vo.page;

import com.github.greatwqs.app.domain.vo.OrderVo;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * order page vo
 *
 * @author greatwqs
 * Create on 2020/7/4
 */
@Getter
@Builder
public class OrderPageVo {

    /**
     * current page index
     */
    private Integer pageIndex;

    /***
     * page item count
     */
    private Integer pageSize;

    /***
     * total page count
     */
    private Integer pageCount;

    /**
     * total item count
     */
    private Integer totalCount;

    /**
     * totalCount for price
     */
    private BigDecimal totalPrice;

    /***
     * current item details list
     */
    private List<OrderVo> orderList;
}
