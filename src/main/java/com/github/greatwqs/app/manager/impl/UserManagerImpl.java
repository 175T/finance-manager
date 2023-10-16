package com.github.greatwqs.app.manager.impl;

import com.github.greatwqs.app.common.exception.AppException;
import com.github.greatwqs.app.common.exception.ErrorCode;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.UserVo;
import com.github.greatwqs.app.manager.UserManager;
import com.github.greatwqs.app.mapper.UserlistMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 管理员用户
 *
 * @author greatwqs
 * Create on 2020/6/27
 */
@Slf4j
@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserlistMapper userlistMapper;

    @Override
    public UserPo getPo(Long userId) {
        return userlistMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserPo getPoByName(String username) {
        return userlistMapper.selectByUserName(username);
    }

    @Override
    @Transactional
    public void insert(UserPo userPo) {
        userlistMapper.insertSelective(userPo);
    }

    @Override
    public UserVo getVo(Long userId) {
        UserPo userPo = userlistMapper.selectByPrimaryKey(userId);
        if (userPo == null || !userPo.getIsvalid()) {
            log.warn("getVo userPo null, userId: " + userId);
            throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
        }
        return modelMapper.map(userPo, UserVo.class);
    }

    @Override
    @Transactional
    public void update(UserPo userPo) {
        userlistMapper.updateByPrimaryKeySelective(userPo);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        UserPo userPo = new UserPo();
        userPo.setIsvalid(false);
        userPo.setId(userId);
        userlistMapper.updateByPrimaryKeySelective(userPo);
    }
}
