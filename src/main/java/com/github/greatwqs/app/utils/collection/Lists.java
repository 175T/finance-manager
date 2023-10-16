package com.github.greatwqs.app.utils.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public final class Lists {

	private Lists() {
	}

	/***
	 * isEmpty
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(Collection<?> list) {
		return list == null || list.isEmpty();
	}

	/***
	 * isNotEmpty
	 * @param list
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> list) {
		return !isEmpty(list);
	}

	/***
	 * newArrayList
	 * @param elements
	 * @param <T>
	 * @return
	 */
	@SafeVarargs
	public static <T> ArrayList<T> newArrayList(T... elements) {
		return com.google.common.collect.Lists.newArrayList(elements);
	}

	/***
	 * newLinkedList
	 * @param elements
	 * @param <T>
	 * @return
	 */
	@SafeVarargs
	public static <T> LinkedList<T> newLinkedList(T... elements) {
		final LinkedList<T> linkedList = new LinkedList<T>();
		if (elements == null || elements.length == 0) {
			return linkedList;
		}
		for (final T t : elements) {
			linkedList.add(t);
		}
		return linkedList;
	}

	/***
	 * newHashSet
	 * @param elements
	 * @param <T>
	 * @return
	 */
	@SafeVarargs
	public static <T> HashSet<T> newHashSet(T... elements) {
		return com.google.common.collect.Sets.newHashSet(elements);
	}

	/****
	 * splitToInt
	 * @param str
	 * @param separator
	 * @return
	 */
	public static List<Integer> splitToInt(String str, String separator) {
		if (StringUtils.isBlank(str)) {
			return Collections.emptyList();
		}
		String[] parts = StringUtils.splitByWholeSeparator(str, separator);
		List<Integer> result = new ArrayList<Integer>(parts.length);
		for (String num : parts) {
			if (NumberUtils.isDigits(num)) {
				result.add(Integer.valueOf(num));
			}
		}
		return result;
	}

	/***
	 * removeNull
	 * @param list
	 */
	public static void removeNull(Collection<?> list) {
		if (isEmpty(list)) {
			return;
		}
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext(); ) {
			if (iterator.next() == null) {
				iterator.remove();
			}
		}
	}

	/***
	 * Collection size
	 * @param list
	 * @return
	 */
	public static int size(Collection<?> list) {
		return list == null ? 0 : list.size();
	}
}
