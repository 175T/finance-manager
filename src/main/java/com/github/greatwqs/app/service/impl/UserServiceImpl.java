package com.github.greatwqs.app.service.impl;

import com.github.greatwqs.app.common.exception.AppException;
import com.github.greatwqs.app.common.exception.ErrorCode;
import com.github.greatwqs.app.domain.po.UserLoginHistoryPo;
import com.github.greatwqs.app.domain.vo.UserVo;
import com.github.greatwqs.app.manager.UserManager;
import com.github.greatwqs.app.mapper.UserlistLoginHistoryMapper;
import com.github.greatwqs.app.mapper.UserlistMapper;
import com.github.greatwqs.app.utils.PublicUtils;
import com.github.greatwqs.app.utils.collection.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.greatwqs.app.common.AppConstants.*;

/**
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserlistMapper userlistMapper;

    @Autowired
    private UserlistLoginHistoryMapper userlistLoginHistoryMapper;

    @Autowired
    private UserManager userManager;

    @Override
    @Transactional
    public String login(String username, String password, String loginIp) {
        UserPo userPo = this.userManager.getPoByName(username);
        if (userPo == null || !StringUtils.equals(userPo.getPassword(), password)) {
            log.warn("user login not ok, username: " + username + ", password: " + password);
            return StringUtils.EMPTY;
        }

        final String loginToken = PublicUtils.getUuid();
        log.info("user login ok, username: " + username + ", password: " + password);
        this.insertLoginHistory(userPo, loginToken, loginIp);
        return loginToken;
    }

    private void insertLoginHistory(UserPo userPo, String loginToken, String loginIp) {
        UserLoginHistoryPo history = new UserLoginHistoryPo();
        history.setId(null);
        history.setUserid(userPo.getId());
        history.setLoginip(loginIp);
        history.setLogintoken(loginToken);
        history.setExpiretime(new Date(System.currentTimeMillis() + USER_LOGIN_SESSION_EXPIRE_TIME));
        history.setIsvalid(true);
        history.setCreatetime(new Date());
        history.setUpdatetime(new Date());
        userlistLoginHistoryMapper.insertSelective(history);
    }

    @Override
    @Transactional
    public void logout(UserPo userPo, String loginToken) {
        userlistLoginHistoryMapper.updateIsValid(loginToken, userPo.getId());
    }

    @Override
    public List<UserVo> allUsers(UserPo userPo) {
        if (!userPo.getIssuperadmin()) {
            return Lists.newLinkedList();
        }
        List<UserPo> userPoList = userlistMapper.selectAllUsers();
        return userPoList.stream().map(po -> poToVo(po)).collect(Collectors.toList());
    }

    private UserVo poToVo(UserPo userPo) {
        return modelMapper.map(userPo, UserVo.class);
    }

    @Override
    @Transactional
    public Long create(String username, String content, UserPo userPo) {
        if (!userPo.getIssuperadmin()) {
            log.warn("only super admin can create user. userPoName: " + userPo.getUsername());
            throw new AppException(ErrorCode.BAD_REQUEST);
        }

        UserPo userPoInsert = new UserPo();
        userPoInsert.setId(null);
        userPoInsert.setUsername(StringUtils.trimToEmpty(username));
        userPoInsert.setContent(StringUtils.trimToEmpty(content));
        userPoInsert.setPassword(PublicUtils.getUuid());
        userPoInsert.setIssuperadmin(false);
        userPoInsert.setIsvalid(true);
        userPoInsert.setCreatetime(new Date());
        userPoInsert.setUpdatetime(new Date());
        userlistMapper.insert(userPoInsert);
        return userPoInsert.getId();
    }

    @Override
    @Transactional
    public void delete(Long deletedUserId, UserPo userPo) {
        if (!userPo.getIssuperadmin()) {
            log.warn("delete user, current userId not super admin, userId: " + userPo.getId());
            return;
        }

        // 不能删除超级管理员
        UserPo goingDeleteUserPo = userManager.getPo(deletedUserId);
        if (goingDeleteUserPo.getIssuperadmin()) {
            log.warn("delete user, can not delete super admin: " + deletedUserId);
            return;
        }

        UserPo updateUserPo = new UserPo();
        updateUserPo.setId(deletedUserId);
        updateUserPo.setIsvalid(false);
        updateUserPo.setUpdatetime(new Date());
        userlistMapper.updateByPrimaryKeySelective(updateUserPo);
    }

    @Override
    @Transactional
    public void updatePassword(UserPo userPo, String oldPassword, String newPassword, String newPasswordConfirm) {
        this.updatePasswordCheck(userPo, oldPassword, newPassword, newPasswordConfirm);

        UserPo updateUserPo = new UserPo();
        updateUserPo.setId(userPo.getId());
        updateUserPo.setPassword(newPassword);
        updateUserPo.setUpdatetime(new Date());
        userlistMapper.updateByPrimaryKeySelective(updateUserPo);
    }

    public void updatePasswordCheck(UserPo userPo, String oldPassword, String newPassword, String newPasswordConfirm) {
        if (StringUtils.isEmpty(oldPassword)
                || StringUtils.isEmpty(newPassword)
                || StringUtils.isEmpty(newPasswordConfirm)) {
            log.warn("update password, password can not empty. oldPassword: " + oldPassword
                    + ", newPassword: " + newPassword
                    + ", newPasswordConfirm: " + newPasswordConfirm);
            throw new AppException(ErrorCode.UPDATE_PASSWORD_CAN_NOT_EMPTY);
        }

        if (!StringUtils.equals(userPo.getPassword(), oldPassword)) {
            log.warn("update password, old password not match. userId: " + userPo.getId()
                    + ", request param oldPassword: " + oldPassword);
            throw new AppException(ErrorCode.UPDATE_PASSWORD_OLD_PASSWORD_NOT_MATCH);
        }

        if (!StringUtils.equals(newPassword, newPasswordConfirm)) {
            log.warn("update password, new password not match. newPassword: " + newPassword
                    + ", newPasswordConfirm: " + newPasswordConfirm);
            throw new AppException(ErrorCode.UPDATE_PASSWORD_NEW_PASSWORD_CONFIRM_NOT_MATCH);
        }

        if (StringUtils.length(newPassword) < PASSWORD_MIN_LENGTH
                || StringUtils.length(newPassword) > PASSWORD_MAX_LENGTH) {
            log.warn("update password, newPassword length not ok. newPassword: " + newPassword);
            throw new AppException(ErrorCode.UPDATE_PASSWORD_NEW_PASSWORD_LENGTH_NOT_OK);
        }
    }
}
