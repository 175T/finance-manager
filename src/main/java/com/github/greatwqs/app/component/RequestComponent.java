package com.github.greatwqs.app.component;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.greatwqs.app.domain.po.UserPo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.github.greatwqs.app.common.AppConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求组件
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
@Component
public class RequestComponent {

    public HttpServletRequest getRequest() {
        if (getRequestAttributes() != null) {
            return getRequestAttributes().getRequest();
        } else {
            return null;
        }
    }

    public String getRequestUri() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "unkonw uri";
        } else {
            return request.getRequestURI();
        }
    }

    public String getRequestUrl() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "unkonw url";
        } else {
            return request.getRequestURL().toString();
        }
    }

    public HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    /***
     * getExecuteIp
     * @return
     */
    public String getClientIp() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip != null && ip.trim().length() > 0) {
            int index = ip.indexOf(',');
            return (index != -1) ? ip.substring(0, index) : ip;
        }
        return ip;
    }

    /***
     * default zh_CN
     * @return
     */
    public Locale getLocale() {
        return AppConstants.LOCALE;
    }

    /***
     * get locale
     * @return
     */
    private Locale getRequestLocale() {
        HttpServletRequest request = getRequest();
        final String APP_LOCALE = "app_locale";
        final Cookie cookie = WebUtils.getCookie(request, APP_LOCALE);
        if (cookie != null && StringUtils.isNotEmpty(cookie.getValue())) {
            return org.springframework.util.StringUtils.parseLocaleString(cookie.getValue());
        }

        /**
         * based on the Accept-Language header. If the client request
         * doesn't provide an Accept-Language header, this method returns the
         * default locale for the server.
         */
        return request.getLocale();
    }

    public void setParam(String key, Object value) {
        RequestContextHolder.currentRequestAttributes()
                .setAttribute(key, value, RequestAttributes.SCOPE_REQUEST);
    }

    public Object getParam(String key) {
        return RequestContextHolder.currentRequestAttributes()
                .getAttribute(key, RequestAttributes.SCOPE_REQUEST);
    }

    public UserPo getLoginUser() {
        return (UserPo) RequestContextHolder.currentRequestAttributes()
                .getAttribute(AppConstants.REQUEST_USER, RequestAttributes.SCOPE_REQUEST);
    }

    public String getLoginToken() {
        return (String) RequestContextHolder.currentRequestAttributes()
                .getAttribute(AppConstants.REQUEST_USER_TOKEN, RequestAttributes.SCOPE_REQUEST);
    }
}
