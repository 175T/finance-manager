package com.github.greatwqs.app.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author greatwqs
 * Create on 2022/2/24
 */
public class FinanceUtils {

    /***
     * 替换字符串中JS的敏感字符串: 回车, 两边的空格, 引号, 括号
     * @param orderDes
     * @return
     */
    public static String trimJsChar(String orderDes){
        // 删除两边的空格
        orderDes = StringUtils.trimToEmpty(orderDes);

        // 删除回车
        orderDes = orderDes.replace("\n"," ");

        // 删除单引号和双引号
        orderDes = orderDes.replace("'"," ");
        orderDes = orderDes.replace("\""," ");

        // 删除括号
        orderDes = orderDes.replace("("," ");
        orderDes = orderDes.replace(")"," ");

        if (StringUtils.isEmpty(orderDes)){
            return "空";
        }
        return StringUtils.trimToEmpty(orderDes);
    }

    public static void main(String[] args) {
        String aaa = " tar -cvf xiangjun-demo.tar xiangjun-demo/\n" +
                "\n" +
                "scp xiangjun-demo.tar root@47.100.238.23:/root\n ";
        System.out.println(trimJsChar(aaa));
    }
}
