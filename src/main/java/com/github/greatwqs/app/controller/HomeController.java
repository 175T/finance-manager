package com.github.greatwqs.app.controller;

import com.github.greatwqs.app.component.RequestComponent;
import com.github.greatwqs.app.interceptor.annotation.LoginRequired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.greatwqs.app.common.AppConstants.PAGE_ORDER_QUERY;

/**
 * 首页 / 管理首页
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
@RequestMapping("")
@Controller
public class HomeController {

    @Autowired
    private RequestComponent requestComponent;

    /***
     * 首页! 如果登录就进入管理页面, 否则进入登录页面!
     */
    @LoginRequired
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void defaultHome(HttpServletResponse response) throws IOException {
        log.info("defaultHome page, uri: " + requestComponent.getRequestUri()
                + ", clientIP: " + requestComponent.getClientIp());
        response.setStatus(HttpStatus.FOUND.value());
        response.sendRedirect(PAGE_ORDER_QUERY);
    }
}
