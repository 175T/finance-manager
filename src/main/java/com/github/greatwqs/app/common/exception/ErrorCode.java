package com.github.greatwqs.app.common.exception;

import com.github.greatwqs.app.common.AppConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * ErrorCode, define for return code.
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public enum ErrorCode {

    /**
     * HTTP STATUS CODE [0-1000)
     **/
    NORMAL_SUCCESS(200, HttpStatus.OK.value()),
    BAD_REQUEST(400, HttpStatus.BAD_REQUEST.value()),
    UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED.value()),
    FORBIDDEN(403, HttpStatus.FORBIDDEN.value()),
    NOT_FOUND(404, HttpStatus.NOT_FOUND.value()),
    METHOD_NOT_ALLOWED(405, HttpStatus.METHOD_NOT_ALLOWED.value()),
    CONFLICT(409, HttpStatus.CONFLICT.value()),
    UNSUPPORTED_MEDIA_TYPE(415, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * Bisiness ERROR [1000-2000)
     **/
    BIZ_UNKNOWN_ERROR(1000, HttpStatus.INTERNAL_SERVER_ERROR.value()),
    USER_LOGIN_ERROR(1001, HttpStatus.FOUND.value()),
    USER_LOGIN_TOKEN_ERROR(1002, HttpStatus.FOUND.value()),
    SUBJECT_NOT_FOUND(1003, HttpStatus.NOT_FOUND.value()),
    TICKET_TYPE_NOT_FOUND(1004, HttpStatus.NOT_FOUND.value()),
    ORDER_TIME_CAN_NOT_UPDATE_OR_DELETE(1005, HttpStatus.BAD_REQUEST.value()),
    ORDER_NOT_FOUND(1006, HttpStatus.NOT_FOUND.value()),
    UPDATE_PASSWORD_CAN_NOT_EMPTY(1007, HttpStatus.BAD_REQUEST.value()),
    UPDATE_PASSWORD_OLD_PASSWORD_NOT_MATCH(1008, HttpStatus.BAD_REQUEST.value()),
    UPDATE_PASSWORD_NEW_PASSWORD_CONFIRM_NOT_MATCH(1009, HttpStatus.BAD_REQUEST.value()),
    UPDATE_PASSWORD_NEW_PASSWORD_LENGTH_NOT_OK(1010, HttpStatus.BAD_REQUEST.value()),

    /**
     * Outer Call ERROR [2000+)
     **/
    OUT_UNKNOWN_ERROR(3000, HttpStatus.INTERNAL_SERVER_ERROR.value());


    private Integer errorCode;
    private Integer httpCode;
    private String errorMsgKey;

    ErrorCode(Integer errorCode, Integer httpCode) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
    }

    ErrorCode(Integer errorCode, Integer httpCode, String errorMsgKey) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.errorMsgKey = errorMsgKey;
    }

    /***
     * get error message key.
     * @return example `error.code.2000`
     */
    public String getErrorMsgKey() {
        if (StringUtils.isNotEmpty(errorMsgKey)) {
            return errorMsgKey;
        }
        return AppConstants.ERROR_CODE_PREFIX_KEY + this.errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer getHttpCode() {
        return httpCode;
    }
}
