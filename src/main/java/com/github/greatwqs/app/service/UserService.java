package com.github.greatwqs.app.service;

import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.UserVo;

import java.util.List;

/**
 *
 * 用户管理员模块
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public interface UserService {

	/***
	 * 用户登录, 登录成功返回LoginToken, 否则返回null
	 * @param username
	 * @param password
	 * @return
	 */
	String login(String username, String password, String loginIp);

	/**
	 * 用户退出登录
	 * @param userPo
	 * @return
	 */
	void logout(UserPo userPo, String loginToken);

	/***
	 * 返回所有用户.
	 * userPo 如果是超级用户, 返回所有的用户列表.
	 * 如果是普通用户, 返回为空!
	 * @param userPo
	 * @return
	 */
	List<UserVo> allUsers(UserPo userPo);

	/***
	 * 用户添加, 返回创建的用户ID
	 * @param username
	 * @param content
	 * @param userPo
	 * @return
	 */
	Long create(String username, String content, UserPo userPo);

	/**
	 * 删除用户
	 * @param deletedUserId 需要被删除的用户ID
	 * @param userPo 当前登录管理员
	 */
	void delete(Long deletedUserId, UserPo userPo);

	/***
	 * 用户自己更新自己密码
	 * @param userPo
	 * @param oldPassword
	 * @param newPassword
	 * @param newPasswordConfirm
	 */
	void updatePassword(UserPo userPo, String oldPassword, String newPassword, String newPasswordConfirm);
}
