package com.github.greatwqs.app.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * DoubleUtilTest
 */
@RunWith(JUnit4.class)
public class DoubleUtilTest {

	Double v1 = 20D;
	Double v2 = 1.23D;

	@Test
	public void add() {
		Assert.assertEquals(DoubleUtil.add(v1, v2), new Double(21.23));
	}

	@Test
	public void sub() {
		Assert.assertEquals(new Double(DoubleUtil.sub(v1, v2)), new Double(18.77D));
	}

	@Test
	public void mul() {
		Assert.assertEquals(new Double(DoubleUtil.mul(v1, v2)), new Double(24.6D));
	}

	@Test
	public void divide() {
		//		DEF_DIV_SCALE = 2
		Assert.assertEquals(new Double(DoubleUtil.divide(v2, v1)), new Double(0.06D));
	}

	@Test
	public void round() {
		int DEF_DIV_SCALE = 2;
		Assert.assertEquals(new Double(DoubleUtil.round(0.123456789D, DEF_DIV_SCALE)), new Double(0.12D));
	}

}
