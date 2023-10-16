package com.github.greatwqs.app.manager;


import com.github.greatwqs.app.domain.po.OrderPo;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.UserVo;

/**
 *
 * 订单 manager
 *
 * @author greatwqs
 * Create on 2020/11/15
 */
public interface OrderManager {

    /**
     * 获取 OrderPo
     *
     * @param orderId
     * @return
     */
    OrderPo getPoNotNull(Long orderId);

    /***
     * 是否存在 subjectId 的订单记录?
     * @param subjectId
     * @return
     */
    Boolean isSubjectIdOrderExist(Long subjectId);

    /***
     * 是否存在 ticketTypeId 的订单记录?
     * @param ticketTypeId
     * @return
     */
    Boolean isTicketTypeIdOrderExist(Long ticketTypeId);
}
