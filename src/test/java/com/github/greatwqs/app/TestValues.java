package com.github.greatwqs.app;

import com.github.greatwqs.app.domain.po.UserPo;

/**
 *
 * TestValues Constants
 */
public class TestValues {

	public static final String BASE_FILE_PATH = "src/test/resources/";

	public static final UserPo user(String username){
		UserPo user = new UserPo();
		user.setId(1L);
		user.setUsername(username);
		return user;
	}
}
