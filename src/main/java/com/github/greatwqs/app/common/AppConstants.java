package com.github.greatwqs.app.common;

import java.util.Locale;

/**
 * AppConstants
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public class AppConstants {

    // default locale
    public static final Locale LOCALE = new Locale("zh", "CN");

    /**
     * ErrorCode i18n properties prefix key.
     */
    public static final String ERROR_CODE_PREFIX_KEY = "error.code.";

    /**
     * request set attribute names
     * RequestContextHolder.currentRequestAttributes().setAttribute(name, value)
     */
    public static final String REQUEST_USER_TOKEN = "requestUserToken";
    public static final String REQUEST_USER = "requestUser";

    /***
     * 用户登录过期时间, 2小时.
     */
    public static final Long USER_LOGIN_SESSION_EXPIRE_TIME = 1000L * 60 * 60 * 2;

    /***
     * 分页时, 每页 Item 数量
     */
    public static final Integer DEFAULT_PAGE_SIZE = 50;
    // 订单每页 Item 数量
    public static final Integer ORDER_PAGE_SIZE = DEFAULT_PAGE_SIZE;

    /***
     * 用户密码最小/大位数
     */
    public static final Integer PASSWORD_MIN_LENGTH = 8;
    public static final Integer PASSWORD_MAX_LENGTH = 30;

    /***
     * 用户登录成功的cookie
     */
    public static final String COOKIE_LOGIN_TOKEN = "loginToken";
    public static final String APP_LOGIN_TOKEN = COOKIE_LOGIN_TOKEN;

    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_LOGIN_FAILED = "/login_failed.jsp";
    public static final String PAGE_ORDER_QUERY = "/order_query";
    public static final String PAGE_USER_ALL_LIST = "/order_query?isHide=11";
    public static final String PAGE_USER_UPDATE_PASSWORD = "/order_query?isHide=1001";
}
