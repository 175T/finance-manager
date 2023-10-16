package com.github.greatwqs.app.service.impl;

import com.github.greatwqs.app.domain.dto.TicketTypeDto;
import com.github.greatwqs.app.domain.po.TicketTypePo;
import com.github.greatwqs.app.domain.vo.TicketTypeVo;
import com.github.greatwqs.app.manager.OrderManager;
import com.github.greatwqs.app.manager.TicketTypeManager;
import com.github.greatwqs.app.mapper.TicketTypeMapper;
import com.github.greatwqs.app.service.TicketTypeService;
import com.github.greatwqs.app.utils.collection.Lists;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author greatwqs
 * Create on 2020/6/28
 */
@Slf4j
@Service
public class TicketTypeServiceImpl implements TicketTypeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketTypeMapper ticketTypeMapper;

    @Autowired
    private TicketTypeManager ticketTypeManager;

    @Autowired
    private OrderManager orderManager;

    @Override
    public List<TicketTypeVo> getAllTicketTypes() {
        List<TicketTypePo> ticketTypePoList = ticketTypeMapper.selectAll();
        if (Lists.isEmpty(ticketTypePoList)) {
            return Lists.newLinkedList();
        }
        return ticketTypePoList.stream()
                .map(po -> modelMapper.map(po, TicketTypeVo.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long add(TicketTypeDto ticketTypeDto) {
//        TicketTypePo oldPo = ticketTypeManager.getPo(ticketTypeDto.getName());
//        if (oldPo != null) {
//            // name exist, return.
//            log.warn("ticket type name exists, name: " + ticketTypeDto.getName());
//            return 0L;
//        }

        TicketTypePo ticketTypePo = new TicketTypePo();
        ticketTypePo.setId(null);
        ticketTypePo.setName(ticketTypeDto.getName());
        ticketTypePo.setCreateuserid(ticketTypeDto.getCreateuserid());
        ticketTypePo.setIsvalid(true);
        ticketTypePo.setCreatetime(new Date());
        ticketTypePo.setUpdateuserid(ticketTypeDto.getUpdateuserid());
        ticketTypePo.setUpdatetime(new Date());
        ticketTypeMapper.insertSelective(ticketTypePo);
        return ticketTypePo.getId();
    }

    @Override
    @Transactional
    public void update(TicketTypeDto ticketTypeDto) {
        TicketTypePo ticketTypePo = new TicketTypePo();
        ticketTypePo.setId(ticketTypeDto.getId());
        ticketTypePo.setName(ticketTypeDto.getName());
        ticketTypePo.setUpdateuserid(ticketTypeDto.getUpdateuserid());
        ticketTypePo.setUpdatetime(new Date());
        ticketTypeMapper.updateByPrimaryKeySelective(ticketTypePo);
    }

    @Override
    @Transactional
    public Boolean delete(Long tickerTypeId, Long userId) {
        if (orderManager.isTicketTypeIdOrderExist(tickerTypeId)) {
            log.warn("ticket type delete, tickerTypeId exist order, can not delete! tickerTypeId: " + tickerTypeId);
            return false;
        }

        TicketTypePo ticketTypePo = new TicketTypePo();
        ticketTypePo.setId(tickerTypeId);
        ticketTypePo.setIsvalid(false);
        ticketTypePo.setUpdateuserid(userId);
        ticketTypePo.setUpdatetime(new Date());
        ticketTypeMapper.updateByPrimaryKeySelective(ticketTypePo);
        return true;
    }
}
