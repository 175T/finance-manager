package com.github.greatwqs.app.manager.impl;

import com.github.greatwqs.app.common.exception.AppException;
import com.github.greatwqs.app.common.exception.ErrorCode;
import com.github.greatwqs.app.domain.po.OrderPo;
import com.github.greatwqs.app.manager.OrderManager;
import com.github.greatwqs.app.mapper.OrderlistMapper;
import com.github.greatwqs.app.utils.collection.Lists;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单 manager
 *
 * @author greatwqs
 * Create on 2020/11/15
 */
@Slf4j
@Component
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderlistMapper orderlistMapper;

    @Override
    public OrderPo getPoNotNull(Long orderId) {
        OrderPo orderPo = orderlistMapper.selectByPrimaryKey(orderId);
        if (orderPo == null || !orderPo.getIsvalid()) {
            log.error("ORDER_NOT_FOUND, orderId: s%", orderId);
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }
        return orderPo;
    }

    @Override
    public Boolean isSubjectIdOrderExist(Long subjectId) {
        List<OrderPo> orderPoList = orderlistMapper.selectBySubjectId(subjectId, 1);
        if (Lists.isNotEmpty(orderPoList)) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean isTicketTypeIdOrderExist(Long ticketTypeId) {
        List<OrderPo> orderPoList = orderlistMapper.selectByTicketTypeId(ticketTypeId, 1);
        if (Lists.isNotEmpty(orderPoList)) {
            return true;
        }
        return false;
    }
}
