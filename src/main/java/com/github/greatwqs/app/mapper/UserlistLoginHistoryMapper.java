package com.github.greatwqs.app.mapper;

import com.github.greatwqs.app.domain.po.UserLoginHistoryPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by MyBatis Generator 2020/06/27
 * https://github.com/greatwqs/mybatis-generator-plugin
 */
@Mapper
public interface UserlistLoginHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByLoginToken(String loginToken);

    int insert(UserLoginHistoryPo record);

    int insertSelective(UserLoginHistoryPo record);

    UserLoginHistoryPo selectByPrimaryKey(Long id);

    UserLoginHistoryPo selectByLoginToken(@Param("loginToken") String loginToken);

    int updateByPrimaryKeySelective(UserLoginHistoryPo record);

    int updateByPrimaryKey(UserLoginHistoryPo record);

    int updateIsValid(@Param("loginToken") String loginToken, @Param("userId") Long userId);
}