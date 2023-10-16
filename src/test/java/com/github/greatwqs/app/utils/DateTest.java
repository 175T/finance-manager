package com.github.greatwqs.app.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.util.Date;

/**
 * @author greatwqs
 * Create on 2020/7/5
 */
@RunWith(JUnit4.class)
public class DateTest {

    @Test
    public void test() throws ParseException {
        String orderTime = "2020-07-02";
        Date date = DateUtils.parseDate(orderTime, "yyyy-MM-dd");
        String format = DateFormatUtils.format(date, "yyyy-MM-dd");
        Assert.assertEquals(orderTime, format);
    }
}
