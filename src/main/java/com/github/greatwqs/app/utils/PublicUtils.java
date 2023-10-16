package com.github.greatwqs.app.utils;

import java.lang.annotation.Annotation;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

/**
 *
 * PublicUtils
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public class PublicUtils {

	/***
	 * getUuid, 36 char
	 * @return
	 */
	public static final String getUuid() {
		return UUID.randomUUID().toString();
	}

	/****
	 * getPageBeginIndex
	 * @param pageNum The first pageNum is 1
	 * @param pageSize
	 * @return
	 */
	public static final Integer getPageBeginIndex(Integer pageNum, Integer pageSize) {
		return (pageNum - 1) * pageSize;
	}

	/***
	 * get Total Page
	 * @param totalRecordCount
	 * @param onePageRecordSize
	 * @return
	 */
	public static final Integer getTotalPage(Integer totalRecordCount, Integer onePageRecordSize) {
		return totalRecordCount / onePageRecordSize + ((totalRecordCount % onePageRecordSize == 0) ? 0 : 1);
	}

	/**
	 * getClazzOrMethodAnnotation from HandlerMethod
	 * 1. get annotation from class.
	 * 2. get annotation from method.
	 * @param handlerMethod
	 * @param annotationClass
	 * @param <T>
	 * @return
	 */
	public static <T extends Annotation> T getClazzOrMethodAnnotation(HandlerMethod handlerMethod,
		Class<T> annotationClass) {
		Class<?> clazz = handlerMethod.getBeanType();
		T annotation = clazz.getAnnotation(annotationClass);
		if (annotation != null) {
			return annotation;
		}

		return handlerMethod.getMethod().getAnnotation(annotationClass);
	}

	public static <T extends Annotation> T findClazzOrMethodAnnotation(HandlerMethod handlerMethod,
		Class<T> annotationClass) {
		Class<?> clazz = handlerMethod.getBeanType();

		T annotation = AnnotationUtils.findAnnotation(clazz, annotationClass);
		if (annotation != null) {
			return annotation;
		}

		return AnnotationUtils.findAnnotation(handlerMethod.getMethod(), annotationClass);
	}

	/***
	 * isEnglishLetterOrNumber,
	 * is all the cs param String in [A-Z] || [a-z] || [0-9]
	 * @param cs
	 * @return
	 */
	public static boolean isEnglishLetterOrNumber(final CharSequence cs) {
		if (StringUtils.isEmpty(cs)) {
			return false;
		}

		final int length = cs.length();
		for (int index = 0; index < length; index++) {
			if (isEnglishLetterOrNumber(cs.charAt(index)) == false) {
				return false;
			}
		}
		return true;
	}

	/***
	 * isEnglishLetterOrNumber,
	 * is the char in [A-Z] || [a-z] || [0-9]
	 * @param letter
	 * @return
	 */
	private static boolean isEnglishLetterOrNumber(final char letter) {
		if (letter >= 48 && letter <= 57) {
			return true; // 0-9
		}
		return isEnglishLetter(letter);
	}

	/***
	 * isEnglishLetter, is all the cs param String in [A-Z] || [a-z]
	 * @param cs
	 * @return
	 */
	public static boolean isEnglishLetter(final CharSequence cs) {
		if (StringUtils.isEmpty(cs)) {
			return false;
		}

		final int length = cs.length();
		for (int index = 0; index < length; index++) {
			if (isEnglishLetter(cs.charAt(index)) == false) {
				return false;
			}
		}
		return true;
	}

	/***
	 * isEnglishLetter, is the char in [A-Z] || [a-z]
	 * @param letter
	 * @return
	 */
	private static boolean isEnglishLetter(final char letter) {
		if (letter > 64 && letter < 91) {
			return true;
		} else if (letter > 96 && letter < 123) {
			return true;
		} else {
			return false;
		}
	}

	/***
	 * get call class name
	 * @return
	 */
	public static String getClassName() {
		String classFulName = Thread.currentThread().getStackTrace()[2].getClassName();
		int dotIndex = classFulName.lastIndexOf(".");
		return classFulName.substring(dotIndex + 1);
	}

	/***
	 * get call method name
	 * @return
	 */
	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

}
