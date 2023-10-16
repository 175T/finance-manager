package com.github.greatwqs.app.domain.po;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author greatwqs
 * Create on 2020/6/25
 */
@Getter
@Setter
@ToString
public class UserPo {

	private Long id;

	private String username;

	private String password;

	private String content;

	private Boolean issuperadmin;

	private Boolean isvalid;

	private Date createtime;

	private Date updatetime;
}
