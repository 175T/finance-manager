package com.github.greatwqs.app.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author greatwqs
 * Create on 2020/6/25
 */
@Getter
@Setter
@ToString
public class UserVo {

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private String address;

	private Boolean issuperadmin;

	private Boolean isvalid;

	private Date createtime;

	private Date updatetime;
}
