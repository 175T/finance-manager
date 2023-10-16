package com.github.greatwqs.app.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * https://www.cnblogs.com/yingsong/p/5852537.html
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public class DoubleUtil {

	private static final Integer DEF_DIV_SCALE = 2;

	/***
	 * add
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static Double add(Double value1, Double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.add(b2).doubleValue();
	}

	/***
	 * sub
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static double sub(Double value1, Double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.subtract(b2).doubleValue();
	}

	/***
	 * mul
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static Double mul(Double value1, Double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * divide
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static Double divide(Double dividend, Double divisor) {
		return divide(dividend, divisor, DEF_DIV_SCALE);
	}

	/***
	 * divide
	 * @param dividend
	 * @param divisor
	 * @param scale
	 * @return
	 */
	public static Double divide(Double dividend, Double divisor, Integer scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor));
		return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
	}

	/***
	 * round
	 * @param value
	 * @param scale
	 * @return
	 */
	public static double round(double value, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bdValue = new BigDecimal(Double.toString(value));
		BigDecimal one = new BigDecimal("1");
		return bdValue.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
	}
}
