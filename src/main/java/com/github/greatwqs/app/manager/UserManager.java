package com.github.greatwqs.app.manager;


import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.UserVo;

/**
 *
 * 管理员用户
 *
 * @author greatwqs
 * Create on 2020/6/27
 */
public interface UserManager {

    UserPo getPo(Long userId);

    UserPo getPoByName(String username);

    void insert(UserPo userPo);

    UserVo getVo(Long userId);

    void update(UserPo userPo);

    void delete(Long userId);
}
