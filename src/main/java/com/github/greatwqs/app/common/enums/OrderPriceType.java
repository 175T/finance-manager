package com.github.greatwqs.app.common.enums;

import java.util.Arrays;

/***
 *
 * 用于查询订单列表的字段枚举:
 * 不限(查询全部), 收入(OrderPrice > 0), 支出(OrderPrice < 0) 的字段标示;
 *
 * @author greatwqs
 * Create on 2020/7/4
 */
public enum OrderPriceType {

    ALL(0), INCOME(1), OUTCOME(2);

    private int type;

    OrderPriceType(int type) {
        this.type = type;
    }

    public static OrderPriceType of(int orderPriceType) {
        return Arrays.asList(OrderPriceType.values()).stream()
                .filter(item -> item.getType() == orderPriceType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("OrderPriceType not support: " + orderPriceType));
    }

    public int getType() {
        return this.type;
    }
}
