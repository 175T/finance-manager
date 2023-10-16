package com.github.greatwqs.app.service;

import com.github.greatwqs.app.domain.dto.TicketTypeDto;
import com.github.greatwqs.app.domain.vo.TicketTypeVo;

import java.util.List;

/**
 *
 * 票据类型模块
 *
 * @author greatwqs
 * Create on 2020/7/4
 */
public interface TicketTypeService {

    List<TicketTypeVo> getAllTicketTypes();

    Long add(TicketTypeDto ticketTypeDto);

    void update(TicketTypeDto ticketTypeDto);

    /***
     * 返回 Boolean:
     * true: 删除成功
     * false: 未删除 (exist order)
     * @param ticketTypeId
     * @param userId
     * @return
     */
    Boolean delete(Long ticketTypeId, Long userId);
}
