//package com.github.greatwqs.app.service;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//import com.github.greatwqs.app.mapper.UserlistMapper;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.github.greatwqs.app.BaseSpringServiceTest;
//import com.github.greatwqs.app.TestValues;
//import com.github.greatwqs.app.domain.po.UserPo;
//
///**
// * UserServiceTest
// *
// * @author greatwqs
// * Create on 2020/6/25
// */
//public class UserServiceTest extends BaseSpringServiceTest {
//
//    @MockBean
//    @Autowired
//    private UserlistMapper userMapper;
//
//    @Autowired
//    private UserService userService;
//
//    private UserPo user = TestValues.user("greatwqs");
//
//    @Before
//    public void init() {
//        when(userMapper.selectByPrimaryKey(any())).thenReturn(user);
//    }
//
//    @Test
//    public void findByName() {
//        final Long userId = user.getId();
//        final UserPo user = userService.findByUserId(userId);
//        Assert.assertTrue(user != null);
//        Assert.assertEquals(user.getUsername(), userId);
//    }
//
//}
