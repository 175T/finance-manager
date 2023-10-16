package com.github.greatwqs.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@RequestMapping("jsp")
@Controller
public class TestJspController {

    /**
     * 用JSP呈现试图
     * http://localhost:8080/jsp/test1
     */
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public String test1() {
        return "hello1";
    }

    /**
     * 用JSP呈现试图
     * http://localhost:8080/jsp/test2
     */
    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public ModelAndView test2() {
        ModelAndView modelAndView = new ModelAndView("hello2"); //设置对应JSP的模板文件
        modelAndView.addObject("hi", "测试参数传递"); //设置${hi}标签的值
        return modelAndView;
    }
}
