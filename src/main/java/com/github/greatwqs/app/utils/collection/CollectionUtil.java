package com.github.greatwqs.app.utils.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public class CollectionUtil {
	public static <T> List<T> union(Collection<T> collection1, Collection<T> collection2) {
		return new ArrayList<>(Sets.union(Sets.newHashSet(collection1), Sets.newHashSet(collection2)));
	}

	public static <T, R> Map<R, List<T>> groupBy(Collection<T> collection, Function<T, R> keyGetter) {
		return collection.stream()
			.collect(Collectors.groupingBy(keyGetter));
	}

	public static <T, R> Map<T, R> toMap(Collection<R> collection, Function<R, T> keyGetter) {
		Map<T, R> ret = Maps.newHashMap();
		for (R r : collection) {
			ret.put(keyGetter.apply(r), r);
		}
		return ret;
	}

	/**
	 * Extract filed in Objects in List, "null" will be ignored.
	 */
	public static <T, R> List<R> extractField(Collection<T> collection, Function<T, R> getter) {
		return collection.stream()
			.map(getter)
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
	}
}
