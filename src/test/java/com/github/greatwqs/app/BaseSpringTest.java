package com.github.greatwqs.app;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * base spring test
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public abstract class BaseSpringTest {
}
