package com.github.greatwqs.app.mapper;

import com.github.greatwqs.app.domain.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by MyBatis Generator 2020/06/27
* https://github.com/greatwqs/mybatis-generator-plugin
*/
@Mapper
public interface UserlistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    UserPo selectByPrimaryKey(Long id);

    UserPo selectByUserName(@Param("username") String username);

    List<UserPo> selectAllUsers();

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);
}