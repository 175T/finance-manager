package com.github.greatwqs.app.utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.greatwqs.app.utils.collection.Lists;

/**
 * RegExUtilsTest
 */
@RunWith(JUnit4.class)
public class JsonUtilTest {

	static final long TIME_MS_20190509_18_00_00 = 1557396000000L; // 2019-05-09 18:00:00
	final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Test
	public void getObjectMapper() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		ObjectMapper objectMapper1 = JsonUtil.getObjectMapper(DATE_FORMAT);
		try {
			String dateString = objectMapper1.writeValueAsString(new Date(TIME_MS_20190509_18_00_00));
			Assert.assertEquals("\"2019-05-09 18:00:00\"", dateString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void toList() {
		String listJson = "[1,2,3,4,5,6,7,8]";
		try {
			List<Integer> intList = JsonUtil.toList(listJson, Integer.class);
			Assert.assertEquals(intList, Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void toJson() {
		String aaa = "test";
		Assert.assertEquals(JsonUtil.toJson(aaa), "\"test\"");
	}

	@Test
	public void fromJson1() {
		String folderIdsStr = "[1,2,3]";
		List<Long> folderIds = JsonUtil.fromJson(folderIdsStr, new TypeReference<List<Long>>() {
		});
		Assert.assertEquals(folderIds, Lists.newArrayList(1L, 2L, 3L));
	}

	@Test
	public void fromJson2() {
		String str = "\"test\"";
		String fds = JsonUtil.fromJson(str, String.class);
		Assert.assertEquals(fds, "test");
	}

	@Test
	public void convertToObject() {
		String str = "\"test\"";
		String fds = JsonUtil.fromJson(str, String.class);
		Assert.assertEquals(fds, "test");
	}

}
