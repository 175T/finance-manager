package com.github.greatwqs.app.mapper;

import com.github.greatwqs.app.domain.bo.OrderSearchBo;
import com.github.greatwqs.app.domain.dto.OrderSearchDto;
import com.github.greatwqs.app.domain.po.OrderPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MyBatis Generator 2020/07/04
 * https://github.com/greatwqs/mybatis-generator-plugin
 */
@Mapper
public interface OrderlistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderPo record);

    int insertSelective(OrderPo record);

    OrderPo selectByPrimaryKey(Long id);

    List<OrderPo> selectByOrderSearch(OrderSearchBo searchBo);

    List<OrderPo> selectBySubjectId(@Param("subjectId") Long subjectId, @Param("limitCount") Integer limitCount);

    List<OrderPo> selectByTicketTypeId(@Param("ticketTypeId") Long ticketTypeId, @Param("limitCount") Integer limitCount);

    Integer selectCountByOrderSearch(OrderSearchBo searchBo);

    BigDecimal selectTotalPriceByOrderSearch(OrderSearchBo searchBo);

    int updateByPrimaryKeySelective(OrderPo record);

    int updateByPrimaryKey(OrderPo record);
}
