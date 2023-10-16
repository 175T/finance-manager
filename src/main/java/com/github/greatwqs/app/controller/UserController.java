package com.github.greatwqs.app.controller;

import com.github.greatwqs.app.common.AppConstants;
import com.github.greatwqs.app.common.exception.AppException;
import com.github.greatwqs.app.common.exception.ErrorCode;
import com.github.greatwqs.app.component.RequestComponent;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.UserVo;
import com.github.greatwqs.app.interceptor.annotation.LoginRequired;
import com.github.greatwqs.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.greatwqs.app.common.AppConstants.*;

/**
 * 用户业务模块
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestComponent requestComponent;

    /**
     * 用户详情
     */
    @LoginRequired
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public UserVo getUserInfo() {
        UserPo userPo = requestComponent.getLoginUser();
        return modelMapper.map(userPo, UserVo.class);
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(Model mode,
                      @RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password,
                      HttpServletResponse response) throws IOException {
        final String loginIp = requestComponent.getClientIp();
        final String loginToken = userService.login(username, password, loginIp);
        if (StringUtils.isEmpty(loginToken)) {
            log.warn("user login failed! username: " + username);
            throw new AppException(ErrorCode.USER_LOGIN_ERROR);
        } else {
            log.info("user login success! username: " + username);
            Cookie cookie = new Cookie(COOKIE_LOGIN_TOKEN, loginToken);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            response.setStatus(HttpStatus.FOUND.value());
            response.sendRedirect(PAGE_ORDER_QUERY);
        }
    }

    /***
     * 用户退出登录!
     */
    @LoginRequired
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout(Model mode, HttpServletResponse response) throws IOException {
        final String loginToken = requestComponent.getLoginToken();
        final UserPo userPo = requestComponent.getLoginUser();
        userService.logout(userPo, loginToken);
        log.info("user logout! username: username: " + userPo.getUsername());
        response.setStatus(HttpStatus.FOUND.value());
        response.sendRedirect(AppConstants.PAGE_LOGIN);
    }

    /**
     * 用户添加
     */
    @LoginRequired
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(Model mode,
                    @RequestParam(value = "username") String username,
                    @RequestParam(value = "content") String content,
                    HttpServletResponse response) throws IOException {
        UserPo userPo = requestComponent.getLoginUser();
        Long createdUserId = this.userService.create(username, content, userPo);
        log.info("user add success! username: " + username + ", createdUserId: " + createdUserId);
        response.setStatus(HttpStatus.FOUND.value());
        response.sendRedirect(PAGE_USER_ALL_LIST);
    }

    /***
     * 超级管理员删除用户!
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Model mode, @RequestParam(value = "id") Long userId) throws IOException {
        final UserPo userPo = requestComponent.getLoginUser();
        userService.delete(userId, userPo);

        log.info("user delete! userId: " + userId);
        return "1";
    }

    /**
     * 用户更新密码
     */
    @LoginRequired
    @RequestMapping(value = "password/update", method = RequestMethod.POST)
    public void updatePassword(Model mode,
                               @RequestParam(value = "oldPassword") String oldPassword,
                               @RequestParam(value = "newPassword") String newPassword,
                               @RequestParam(value = "newPasswordConfirm") String newPasswordConfirm,
                               HttpServletResponse response) throws IOException {
        UserPo userPo = requestComponent.getLoginUser();
        this.userService.updatePassword(userPo, oldPassword, newPassword, newPasswordConfirm);
        log.info("user update password success! username: " + userPo.getUsername() + ", newPassword: " + newPassword);

        // logout, user login again with newPassword
        final String loginToken = requestComponent.getLoginToken();
        userService.logout(userPo, loginToken);

        response.setStatus(HttpStatus.FOUND.value());
        response.sendRedirect(PAGE_LOGIN);
    }

    /**
     * 用户检查密码是否匹配 (更新密码是预先检查.)
     */
    @LoginRequired
    @RequestMapping(value = "password/check", method = RequestMethod.POST)
    public String updatePassword(Model mode,
                                 @RequestParam(value = "password") String password) throws IOException {
        UserPo userPo = requestComponent.getLoginUser();
        if (StringUtils.equals(userPo.getPassword(), password)) {
            return "1"; // ok, 可以更新密码
        } else {
            return "0";
        }
    }
}
