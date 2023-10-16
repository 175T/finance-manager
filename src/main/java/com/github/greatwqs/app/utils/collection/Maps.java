package com.github.greatwqs.app.utils.collection;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public final class Maps {

	private Maps() {
	}

	/***
	 * isEmpty
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/***
	 * isNotEmpty
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/****
	 * newHashMap Demo : Maps.newHashMap(Pair.of("A", 1), Pair.of("B", 2))
	 * @param pairs
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	@SafeVarargs
	public static <K, V> HashMap<K, V> newHashMap(Pair<K, V>... pairs) {
		HashMap<K, V> result = new HashMap<K, V>();
		if (ArrayUtils.isNotEmpty(pairs)) {
			for (Pair<K, V> pair : pairs) {
				result.put(pair.getKey(), pair.getValue());
			}
		}
		return result;
	}

}
