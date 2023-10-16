package com.github.greatwqs.app.mapper;

import com.github.greatwqs.app.domain.po.SubjectPo;
import com.github.greatwqs.app.domain.po.TicketTypePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MyBatis Generator 2020/06/28
 * https://github.com/greatwqs/mybatis-generator-plugin
 */
@Mapper
public interface TicketTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TicketTypePo record);

    int insertSelective(TicketTypePo record);

    List<TicketTypePo> selectByTypeIdList(@Param("ticketTypeIdList") List<Long> ticketTypeIdList);

    List<TicketTypePo> selectAll();

    TicketTypePo selectByPrimaryKey(Long id);

    TicketTypePo selectByName(@Param("name") String name);

    int updateByPrimaryKeySelective(TicketTypePo record);

    int updateByPrimaryKey(TicketTypePo record);
}