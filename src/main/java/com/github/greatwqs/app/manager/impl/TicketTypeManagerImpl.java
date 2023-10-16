package com.github.greatwqs.app.manager.impl;

import com.github.greatwqs.app.common.exception.AppException;
import com.github.greatwqs.app.common.exception.ErrorCode;
import com.github.greatwqs.app.domain.po.SubjectPo;
import com.github.greatwqs.app.domain.po.TicketTypePo;
import com.github.greatwqs.app.domain.vo.SubjectVo;
import com.github.greatwqs.app.domain.vo.TicketTypeVo;
import com.github.greatwqs.app.manager.SubjectManager;
import com.github.greatwqs.app.manager.TicketTypeManager;
import com.github.greatwqs.app.mapper.SubjectlistMapper;
import com.github.greatwqs.app.mapper.TicketTypeMapper;
import com.github.greatwqs.app.utils.collection.Lists;
import com.github.greatwqs.app.utils.collection.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 票据类型
 *
 * @author greatwqs
 * Create on 2020/6/28
 */
@Slf4j
@Component
public class TicketTypeManagerImpl implements TicketTypeManager {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketTypeMapper ticketTypeMapper;

    @Override
    @Transactional
    public void insert(TicketTypePo ticketTypePo) {
        ticketTypeMapper.insertSelective(ticketTypePo);
    }

    @Override
    public TicketTypePo getPo(String ticketTypeName) {
        return ticketTypeMapper.selectByName(StringUtils.trimToEmpty(ticketTypeName));
    }

    @Override
    public List<TicketTypePo> getPoList(List<Long> ticketTypeIdList) {
        if (Lists.isEmpty(ticketTypeIdList)) {
            return Lists.newLinkedList();
        }

        return this.ticketTypeMapper.selectByTypeIdList(ticketTypeIdList);
    }

    @Override
    public TicketTypeVo getVo(Long ticketTypeId) {
        TicketTypePo ticketTypePo = ticketTypeMapper.selectByPrimaryKey(ticketTypeId);
        if (ticketTypePo == null || !ticketTypePo.getIsvalid()) {
            log.warn("getVo ticketTypePo null, ticketTypeId: " + ticketTypeId);
            throw new AppException(ErrorCode.TICKET_TYPE_NOT_FOUND);
        }
        return modelMapper.map(ticketTypePo, TicketTypeVo.class);
    }


    @Override
    public List<TicketTypeVo> getVoList(List<Long> ticketTypeIdList) {
        if (Lists.isEmpty(ticketTypeIdList)) {
            return Lists.newLinkedList();
        }

        List<TicketTypePo> ticketTypePoList = this.getPoList(ticketTypeIdList);
        return ticketTypePoList.stream().map(po -> poToVo(po)).collect(Collectors.toList());
    }

    private TicketTypeVo poToVo(TicketTypePo ticketTypePo) {
        return modelMapper.map(ticketTypePo, TicketTypeVo.class);
    }

    @Override
    public Map<Long, TicketTypeVo> getVoMap(List<Long> ticketTypeIdList) {
        if (Lists.isEmpty(ticketTypeIdList)) {
            return Maps.newHashMap();
        }

        List<TicketTypeVo> ticketTypeVoList = this.getVoList(ticketTypeIdList);
        Map<Long, TicketTypeVo> ticketTypeVoMap = Maps.newHashMap();
        ticketTypeVoList.forEach(vo -> ticketTypeVoMap.put(vo.getId(), vo));
        return ticketTypeVoMap;
    }

    @Override
    public Map<String, TicketTypeVo> getNameVoMap() {
        List<TicketTypePo> ticketTypePoList = ticketTypeMapper.selectAll();
        if (Lists.isEmpty(ticketTypePoList)) {
            return Maps.newHashMap();
        }

        Map<String, TicketTypeVo> ticketTypeVoMap = Maps.newHashMap();
        for (TicketTypePo ticketTypePo : ticketTypePoList) {
            ticketTypeVoMap.put(ticketTypePo.getName(), modelMapper.map(ticketTypePo, TicketTypeVo.class));
        }
        return ticketTypeVoMap;
    }

    @Override
    @Transactional
    public void update(TicketTypePo ticketTypePo) {
        ticketTypeMapper.updateByPrimaryKeySelective(ticketTypePo);
    }

    @Override
    @Transactional
    public void delete(Long ticketTypeId, Long userId) {
        TicketTypePo ticketTypePo = new TicketTypePo();
        ticketTypePo.setIsvalid(false);
        ticketTypePo.setId(ticketTypeId);
        ticketTypePo.setUpdateuserid(userId);
        ticketTypeMapper.updateByPrimaryKeySelective(ticketTypePo);
    }
}
