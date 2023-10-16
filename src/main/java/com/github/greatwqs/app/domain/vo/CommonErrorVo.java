package com.github.greatwqs.app.domain.vo;

/**
 * CommonErrorVo
 * 异常错误返回的数据
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public class CommonErrorVo {

	private Integer errorCode;
	private String errorMessage;

	public CommonErrorVo() {
	}

	public CommonErrorVo(Integer errCode, String errMsg) {
		this.errorCode = errCode;
		this.errorMessage = errMsg;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
