package com.github.greatwqs.app.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.greatwqs.app.domain.po.UserLoginHistoryPo;
import com.github.greatwqs.app.mapper.UserlistLoginHistoryMapper;
import com.github.greatwqs.app.mapper.UserlistMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.github.greatwqs.app.common.AppConstants;
import com.github.greatwqs.app.common.exception.AppException;
import com.github.greatwqs.app.common.exception.ErrorCode;
import com.github.greatwqs.app.component.RequestComponent;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.interceptor.annotation.LoginRequired;
import com.github.greatwqs.app.utils.PublicUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import static com.github.greatwqs.app.common.AppConstants.USER_LOGIN_SESSION_EXPIRE_TIME;

/**
 * LoginInterceptor
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RequestComponent requestComponent;

    @Autowired
    private UserlistMapper userlistMapper;

    @Autowired
    private UserlistLoginHistoryMapper userlistLoginHistoryMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequired = PublicUtils.getClazzOrMethodAnnotation(handlerMethod, LoginRequired.class);
        if (loginRequired == null) {
            return true;
        }

        final String token = this.getLoginTokenNotNull(request);
        final UserPo loginUser = this.getUserNotNull(token);
        RequestContextHolder.currentRequestAttributes()
                .setAttribute(AppConstants.REQUEST_USER, loginUser, RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.currentRequestAttributes()
                .setAttribute(AppConstants.REQUEST_USER_TOKEN, token, RequestAttributes.SCOPE_REQUEST);
        return true;
    }

    private UserLoginHistoryPo getHistoryPoNotNull(String loginToken) {
        UserLoginHistoryPo history = userlistLoginHistoryMapper.selectByLoginToken(loginToken);
        if (history == null || System.currentTimeMillis() > history.getExpiretime().getTime()) {
            log.warn("history null or expired, loginToken: " + loginToken);
            throw new AppException(ErrorCode.USER_LOGIN_TOKEN_ERROR);
        }

        this.refreshExpireTime(history);
        return history;
    }

    /***
     * get user not null from login token
     * @param loginToken
     * @return
     */
    private UserPo getUserNotNull(String loginToken) {
        UserLoginHistoryPo historyPo = this.getHistoryPoNotNull(loginToken);
        UserPo userPo = userlistMapper.selectByPrimaryKey(historyPo.getUserid());
        if (userPo == null) {
            log.warn("userPo null, userId: " + historyPo.getUserid());
            throw new AppException(ErrorCode.USER_LOGIN_TOKEN_ERROR);
        }
        return userPo;
    }

    /**
     * get user login token not null
     *
     * @param request
     * @return
     */
    private String getLoginTokenNotNull(HttpServletRequest request) {
        String token = getLoginToken(request);
        if (StringUtils.isEmpty(token)) {
            log.warn("token is em, url: " + requestComponent.getRequestUrl());
            throw new AppException(ErrorCode.USER_LOGIN_TOKEN_ERROR);
        }
        return token;
    }

    /**
     * get user login token
     *
     * @param request
     * @return
     */
    private String getLoginToken(HttpServletRequest request) {
        final Cookie cookie = WebUtils.getCookie(request, AppConstants.COOKIE_LOGIN_TOKEN);
        if (cookie != null && StringUtils.isNotEmpty(cookie.getValue())) {
            return cookie.getValue();
        }
        return request.getHeader(AppConstants.COOKIE_LOGIN_TOKEN);
    }

    /***
     * 刷新过期时间
     * @param history
     */
    private void refreshExpireTime(UserLoginHistoryPo history) {
        // 在24小时内可以随意刷新过期时间
        final long refreshWithinTime = 1000L * 60 * 60 * 24;
        if (System.currentTimeMillis() - history.getCreatetime().getTime() < refreshWithinTime) {
            UserLoginHistoryPo updatePo = new UserLoginHistoryPo();
            updatePo.setId(history.getId());
            updatePo.setExpiretime(new Date(System.currentTimeMillis() + USER_LOGIN_SESSION_EXPIRE_TIME));
            updatePo.setUpdatetime(new Date());
            userlistLoginHistoryMapper.updateByPrimaryKeySelective(updatePo);
        }
    }
}
