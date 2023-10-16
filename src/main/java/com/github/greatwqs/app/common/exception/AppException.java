package com.github.greatwqs.app.common.exception;

import java.util.List;

/**
 *
 * AppException
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public class AppException extends RuntimeException {

    private ErrorCode errorCode;

    // rewrite default msg in i18n/message.properties
    private String errorMsg;

    private List<Object> errorMsgParams;

    /***
     * use default msg in i18n/message.properties
     * @param errorCode
     */
    public AppException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /***
     *  use default msg with ${errorMsgParams} in i18n/message.properties
     * @param errorCode
     * @param errorMsgParams
     */
    public AppException(ErrorCode errorCode, List<Object> errorMsgParams) {
        this.errorCode = errorCode;
        this.errorMsgParams = errorMsgParams;
    }

    /***
     * rewrite default msg
     * @param errorCode
     * @param errorMsg
     */
    public AppException(ErrorCode errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<Object> getErrorMsgParams() {
        return errorMsgParams;
    }

    public void setErrorMsgParams(List<Object> errorMsgParams) {
        this.errorMsgParams = errorMsgParams;
    }
}
