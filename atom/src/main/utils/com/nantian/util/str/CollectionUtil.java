package com.nantian.util.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 用于处理集合元素的工具类
 * 
 * @author xurui
 * @version 1.0
 * @created 2013-10-12 上午9:53:48
 */
public class CollectionUtil {
	/**
	 * 求两个List集合元素的交集 建议根据业务原则重写equals
	 * 
	 * @author xurui
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T> List<T> intersect(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList(Arrays.asList(new Object[list1.size()]));
		Collections.copy(list, list1);
		list.retainAll(list2);
		return list;
	}

	/**
	 * 求两个List集合元素的并集 建议根据业务原则重写equals
	 * 
	 * @author xurui
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T> List<T> union(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList(Arrays.asList(new Object[list1.size()]));
		Collections.copy(list, list1);
		list.addAll(list2);
		return list;
	}

	/**
	 * 求两个List集合元素的差集 list1 减 list2 建议根据业务原则重写equals
	 * 
	 * @author xurui
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T> List<T> diff(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList(Arrays.asList(new Object[list1.size()]));
		Collections.copy(list, list1);
		list.removeAll(list2);
		return list;
	}
}
