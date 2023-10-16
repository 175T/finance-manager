package com.github.greatwqs.app.service.impl;

import com.github.greatwqs.app.common.exception.AppException;
import com.github.greatwqs.app.common.exception.ErrorCode;
import com.github.greatwqs.app.domain.bo.OrderSearchBo;
import com.github.greatwqs.app.domain.dto.OrderDownloadDto;
import com.github.greatwqs.app.domain.dto.OrderDto;
import com.github.greatwqs.app.domain.dto.OrderSearchDto;
import com.github.greatwqs.app.domain.po.OrderPo;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.OrderVo;
import com.github.greatwqs.app.domain.vo.SubjectVo;
import com.github.greatwqs.app.domain.vo.TicketTypeVo;
import com.github.greatwqs.app.domain.vo.page.OrderPageVo;
import com.github.greatwqs.app.manager.OrderManager;
import com.github.greatwqs.app.manager.SubjectManager;
import com.github.greatwqs.app.manager.TicketTypeManager;
import com.github.greatwqs.app.mapper.OrderlistMapper;
import com.github.greatwqs.app.service.OrderService;
import com.github.greatwqs.app.utils.FinanceUtils;
import com.github.greatwqs.app.utils.PublicUtils;
import com.github.greatwqs.app.utils.collection.Lists;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.greatwqs.app.common.AppConstants.ORDER_PAGE_SIZE;

/**
 * @author greatwqs
 * Create on 2020/7/4
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    // 订单超过多少时间不能进行更新或删除操作 (1天)
    private static final Long CAN_UPDATE_OR_DELETE_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 1;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderlistMapper orderlistMapper;

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private SubjectManager subjectManager;

    @Autowired
    private TicketTypeManager ticketTypeManager;

    @Override
    public OrderPageVo getOrderPageVo(OrderSearchDto searchDto, UserPo userPo) {
        OrderSearchBo searchBo = searchDto.toOrderSearchBo();
        List<OrderPo> orderPoList = orderlistMapper.selectByOrderSearch(searchBo);
        Integer totalCount = orderlistMapper.selectCountByOrderSearch(searchBo);
        BigDecimal totalPrice = orderlistMapper.selectTotalPriceByOrderSearch(searchBo);
        List<OrderVo> orderVoList = poToVo(orderPoList, userPo);
        return OrderPageVo.builder().orderList(orderVoList)
                .totalCount(totalCount)
                .totalPrice(totalPrice != null ? totalPrice : BigDecimal.valueOf(0))
                .pageCount(PublicUtils.getTotalPage(totalCount, ORDER_PAGE_SIZE))
                .pageIndex(searchDto.getPageIndex())
                .pageSize(ORDER_PAGE_SIZE)
                .build();
    }

    /***
     * po 转 vo
     */
    private List<OrderVo> poToVo(List<OrderPo> orderPoList, UserPo userPo) {
        List<OrderVo> orderVoList = orderPoList.stream().map(po -> poToVo(po, userPo)).collect(Collectors.toList());
        this.setSubject(orderVoList);
        this.setTicketType(orderVoList);
        return orderVoList;
    }


    private OrderVo poToVo(OrderPo orderPo, UserPo userPo) {
        OrderVo orderVo = modelMapper.map(orderPo, OrderVo.class);
        orderVo.setCanUpdateOrDelete(this.canUpdateOrDelete(orderPo, userPo));
        return orderVo;
    }


    /***
     * 为 OrderVo 设置 SubjectVo;
     * @param orderVoList
     */
    private void setSubject(List<OrderVo> orderVoList) {
        if (Lists.isEmpty(orderVoList)) {
            return;
        }

        List<Long> subjectIdList = orderVoList.stream()
                .map(OrderVo::getSubjectid)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, SubjectVo> subjectVoMap = subjectManager.getVoMap(subjectIdList);

        for (OrderVo orderVo : orderVoList) {
            orderVo.setSubject(subjectVoMap.get(orderVo.getSubjectid()));
        }
    }

    /***
     * 为 OrderVo 设置 TicketTypeVo;
     * @param orderVoList
     */
    private void setTicketType(List<OrderVo> orderVoList) {
        if (Lists.isEmpty(orderVoList)) {
            return;
        }

        List<Long> ticketTypeIdList = orderVoList.stream()
                .map(OrderVo::getTickettypeid)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, TicketTypeVo> ticketTypeVoMap = ticketTypeManager.getVoMap(ticketTypeIdList);
        for (OrderVo orderVo : orderVoList) {
            orderVo.setTicketType(ticketTypeVoMap.get(orderVo.getTickettypeid()));
        }
    }


    @Override
    public List<OrderVo> queryDownload(OrderDownloadDto downloadDto, UserPo userPo) {
        OrderSearchBo orderSearchBo = downloadDto.toOrderSearchBo();
        List<OrderPo> orderPoList = orderlistMapper.selectByOrderSearch(orderSearchBo);
        List<OrderVo> orderVoList = poToVo(orderPoList, userPo);
        return orderVoList;
    }

    /**
     * 订单是否可以更新或者删除?
     * 如果是超级管理员可以操作, 非超级管理员只能在某段时间内操作!
     *
     * @param orderPo
     * @param userPo
     * @return
     */
    private Boolean canUpdateOrDelete(OrderPo orderPo, UserPo userPo) {
        if (userPo.getIssuperadmin()) {
            return true;
        }

        return System.currentTimeMillis() - orderPo.getCreatetime().getTime() < CAN_UPDATE_OR_DELETE_EXPIRE_TIME;
    }

    private void checkCanUpdateOrDelete(OrderPo po, UserPo userPo) {
        if (!canUpdateOrDelete(po, userPo)) {
            log.error("ORDER_TIME_CAN_NOT_UPDATE_OR_DELETE, orderId: s%", po.getId());
            throw new AppException(ErrorCode.ORDER_TIME_CAN_NOT_UPDATE_OR_DELETE);
        }
    }

    @Override
    @Transactional
    public Long create(OrderDto orderDto, UserPo userPo) {
        OrderPo orderPo = modelMapper.map(orderDto, OrderPo.class);
        orderPo.setId(null);
        orderPo.setIsvalid(true);
        orderPo.setCreatetime(new Date());
        orderPo.setUpdatetime(new Date());

        // trimOrderDes
        orderPo.setOrderdes(FinanceUtils.trimJsChar(orderPo.getOrderdes()));

        orderlistMapper.insertSelective(orderPo);
        return orderPo.getId();
    }

    @Override
    @Transactional
    public void update(OrderDto orderDto, UserPo userPo) {
        OrderPo orderPoExist = orderManager.getPoNotNull(orderDto.getId());
        this.checkCanUpdateOrDelete(orderPoExist, userPo);

        OrderPo orderPo = modelMapper.map(orderDto, OrderPo.class);
        orderPo.setUpdatetime(new Date());

        // trimOrderDes
        orderPo.setOrderdes(FinanceUtils.trimJsChar(orderPo.getOrderdes()));

        orderlistMapper.updateByPrimaryKeySelective(orderPo);
    }

    @Override
    @Transactional
    public void delete(Long orderId, UserPo userPo) {
        OrderPo orderPoExist = orderManager.getPoNotNull(orderId);
        this.checkCanUpdateOrDelete(orderPoExist, userPo);

        OrderPo orderPo = new OrderPo();
        orderPo.setId(orderId);
        orderPo.setIsvalid(false);
        orderPo.setUpdateuserid(userPo.getId());
        orderPo.setUpdatetime(new Date());
        orderlistMapper.updateByPrimaryKeySelective(orderPo);
    }
}
