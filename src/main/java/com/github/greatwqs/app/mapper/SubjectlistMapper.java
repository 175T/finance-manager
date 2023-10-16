package com.github.greatwqs.app.mapper;

import com.github.greatwqs.app.domain.po.SubjectPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MyBatis Generator 2020/06/27
 * https://github.com/greatwqs/mybatis-generator-plugin
 */
@Mapper
public interface SubjectlistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SubjectPo record);

    int insertSelective(SubjectPo record);

    SubjectPo selectByPrimaryKey(Long id);

    List<SubjectPo> selectAll();

    List<SubjectPo> selectByIdList(@Param("subjectIdList") List<Long> subjectIdList);

    int updateByPrimaryKeySelective(SubjectPo record);

    int updateByPrimaryKey(SubjectPo record);
}