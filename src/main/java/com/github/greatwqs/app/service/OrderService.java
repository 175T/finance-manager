package com.github.greatwqs.app.service;

import com.github.greatwqs.app.domain.dto.OrderDownloadDto;
import com.github.greatwqs.app.domain.dto.OrderDto;
import com.github.greatwqs.app.domain.dto.OrderSearchDto;
import com.github.greatwqs.app.domain.dto.TicketTypeDto;
import com.github.greatwqs.app.domain.po.OrderPo;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.OrderVo;
import com.github.greatwqs.app.domain.vo.TicketTypeVo;
import com.github.greatwqs.app.domain.vo.page.OrderPageVo;

import java.util.List;

/**
 * 订单模块 (收入和支出模块)
 *
 * @author greatwqs
 * Create on 2020/7/4
 */
public interface OrderService {

    /***
     * search query order.
     * @param searchDto
     * @param userPo
     * @return
     */
    OrderPageVo getOrderPageVo(OrderSearchDto searchDto, UserPo userPo);

    /***
     * 查询下载订单列表
     *
     * @param downloadDto
     * @param userPo
     * @return
     */
    List<OrderVo> queryDownload(OrderDownloadDto downloadDto, UserPo userPo);

    /**
     * return created orderId
     *
     * @param orderDto
     * @param userPo
     * @return
     */
    Long create(OrderDto orderDto, UserPo userPo);

    /**
     * update
     *
     * @param orderDto
     * @param userPo
     */
    void update(OrderDto orderDto, UserPo userPo);

    /***
     * delete, update isValid = 0
     *
     * @param orderId
     * @param userPo
     */
    void delete(Long orderId, UserPo userPo);
}
