package com.github.greatwqs.app.manager;

import com.github.greatwqs.app.domain.po.TicketTypePo;
import com.github.greatwqs.app.domain.vo.SubjectVo;
import com.github.greatwqs.app.domain.vo.TicketTypeVo;
import com.github.greatwqs.app.utils.collection.Lists;
import com.github.greatwqs.app.utils.collection.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 票据类型
 *
 * @author greatwqs
 * Create on 2020/6/28
 */
public interface TicketTypeManager {

    void insert(TicketTypePo ticketTypePo);

    TicketTypePo getPo(String ticketTypeName);

    List<TicketTypePo> getPoList(List<Long> ticketTypeIdList);

    TicketTypeVo getVo(Long ticketTypeId);

    List<TicketTypeVo> getVoList(List<Long> ticketTypeIdList);

    Map<Long, TicketTypeVo> getVoMap(List<Long> ticketTypeIdList);

    Map<String, TicketTypeVo> getNameVoMap();

    void update(TicketTypePo ticketTypePo);

    void delete(Long ticketTypeId, Long userId);
}
