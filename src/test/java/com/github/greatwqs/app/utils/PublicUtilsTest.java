package com.github.greatwqs.app.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.annotation.Repeat;

/**
 * PublicUtilsTest
 */
@RunWith(JUnit4.class)
public class PublicUtilsTest {

	@Test
	@Repeat(60)
	public void getUuid() {
		Assert.assertEquals(PublicUtils.getUuid().length(), 36);
	}

	@Test
	public void getPageBeginIdex() {
		Assert.assertEquals(PublicUtils.getPageBeginIndex(1, 20).intValue(), 0);
		Assert.assertEquals(PublicUtils.getPageBeginIndex(2, 20).intValue(), 20);
	}

	@Test
	public void getTotalPage() {
		Assert.assertEquals(PublicUtils.getTotalPage(25, 20).intValue(), 2);
		Assert.assertEquals(PublicUtils.getTotalPage(5, 20).intValue(), 1);
		Assert.assertEquals(PublicUtils.getTotalPage(0, 20).intValue(), 0);
	}

	@Test
	public void isEnglishLetterOrNumber() {
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("111"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("222"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("333"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("813232"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("14564"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("78912"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("78912"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("f"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("fd"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("a"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("z"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("A"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("Z"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("AZ"));
		Assert.assertTrue(PublicUtils.isEnglishLetterOrNumber("KLfdsfds"));

		Assert.assertFalse(PublicUtils.isEnglishLetterOrNumber(""));
		Assert.assertFalse(PublicUtils.isEnglishLetterOrNumber(null));
		Assert.assertFalse(PublicUtils.isEnglishLetterOrNumber("$"));
	}

	@Test
	public void isEnglishLetter() {
		Assert.assertTrue(PublicUtils.isEnglishLetter("f"));
		Assert.assertTrue(PublicUtils.isEnglishLetter("fd"));
		Assert.assertTrue(PublicUtils.isEnglishLetter("a"));
		Assert.assertTrue(PublicUtils.isEnglishLetter("z"));
		Assert.assertTrue(PublicUtils.isEnglishLetter("A"));
		Assert.assertTrue(PublicUtils.isEnglishLetter("Z"));
		Assert.assertTrue(PublicUtils.isEnglishLetter("AZ"));
		Assert.assertTrue(PublicUtils.isEnglishLetter("KLfdsfds"));

		Assert.assertFalse(PublicUtils.isEnglishLetter("111"));
		Assert.assertFalse(PublicUtils.isEnglishLetter("222"));
		Assert.assertFalse(PublicUtils.isEnglishLetter("333"));
		Assert.assertFalse(PublicUtils.isEnglishLetter("813232"));
		Assert.assertFalse(PublicUtils.isEnglishLetter("14564"));
		Assert.assertFalse(PublicUtils.isEnglishLetter("78912"));
		Assert.assertFalse(PublicUtils.isEnglishLetter("78912"));

		Assert.assertFalse(PublicUtils.isEnglishLetter(""));
		Assert.assertFalse(PublicUtils.isEnglishLetter(null));
		Assert.assertFalse(PublicUtils.isEnglishLetter("$"));
	}

	@Test
	public void getClassName() {
		Assert.assertEquals(PublicUtils.getClassName(), "PublicUtilsTest");
	}

	@Test
	public void getMethodName() {
		Assert.assertEquals(PublicUtils.getMethodName(), "getMethodName");
	}
}
