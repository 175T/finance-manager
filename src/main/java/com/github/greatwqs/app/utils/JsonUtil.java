package com.github.greatwqs.app.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * JsonUtil
 */
@Slf4j
public class JsonUtil {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
	}

	public static ObjectMapper getObjectMapper(String format) {
		ObjectMapper mapper = new ObjectMapper();
		String formatDate = format;
		if (StringUtils.isEmpty(formatDate)) {
			formatDate = DATE_FORMAT;
		}
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setDateFormat(new SimpleDateFormat(formatDate));
		return mapper;
	}

	/**
	 * This method is just like "fromJson(String json, TypeReference<?> type)".
	 * The purpose of this method is to make api looks more graceful.
	 */
	public static <T> List<T> toList(String json, Class<T> innerType) throws IOException {
		return objectMapper.readValue(json,
			objectMapper.getTypeFactory().constructCollectionType(List.class, innerType));
	}

	public static String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("obj: " + obj + "JsonProcessingException: " + e.getMessage(), e);
		}
		return null;
	}

	public static <T> T fromJson(String json, TypeReference<?> type) {
		try {
			return objectMapper.readValue(json, type);
		} catch (IOException e) {
			log.error("json: " + json + ", IOException: " + e.getMessage(), e);
		}
		return null;
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			log.error("json: " + json + ", IOException: " + e.getMessage(), e);
		}
		return null;
	}

	public static <T> T convertToObject(Object fromObject, Class<T> toClazz) {
		T object = null;
		try {
			object = objectMapper.convertValue(fromObject, toClazz);
		} catch (Exception e) {
			log.error("fromObject: " + fromObject + ", Exception: " + e.getMessage(), e);
		}
		return object;
	}

	public static <T> T convertToObject(Object fromObject, TypeReference<?> type) {
		T object = null;
		try {
			object = objectMapper.convertValue(fromObject, type);
		} catch (Exception e) {
			log.error("fromObject: " + fromObject + ", Exception: " + e.getMessage(), e);
		}
		return object;
	}
}
