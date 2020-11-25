package com.springboot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;

public class ConvertUtils {

	/**
	 * Converts into Map with given Function as key and value as object.
	 */
	public static <K, T> Map<K, T> convertToMap(List<T> objects, Function<T, K> keyFunction) {
		Map<K, T> result = Maps.newHashMap();
		if (CollectionUtils.isEmpty(objects)) {
			return result;
		}
		objects.forEach(o -> result.put(keyFunction.apply(o), o));
		return result;
	}

	/**
	 * Converts into Map with given Function as key and value as list of objects.
	 */
	public static <K, T> Map<K, List<T>> convertToMapWithList(List<T> objects, Function<T, K> keyFunction) {
		Map<K, List<T>> result = Maps.newHashMap();
		if (CollectionUtils.isEmpty(objects)) {
			return result;
		}
		for (T object : objects) {
			List<T> objectList = result.getOrDefault(keyFunction.apply(object), new ArrayList<>());
			objectList.add(object);
			result.put(keyFunction.apply(object), objectList);
		}
		return result;
	}

	/**
	 * Converts into Map with given Function as key and value as an element in given
	 * Object.
	 */
	public static <K, T, V> Map<K, V> convertToMapOfValue(List<T> objects, Function<T, K> keyFunction,
			Function<T, V> valueFunction) {
		Map<K, V> result = Maps.newHashMap();
		if (CollectionUtils.isEmpty(objects)) {
			return result;
		}
		objects.forEach(o -> result.put(keyFunction.apply(o), valueFunction.apply(o)));
		return result;
	}
}
