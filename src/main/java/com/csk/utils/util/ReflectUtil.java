package com.csk.utils.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: 反射工具类。
 * @author HuZhiliang
 * @version 1.0
 * <pre>
 * Modification History:
 * Date         Author       Version     Description
------------------------------------------------------------------
 * 2013-1-5      HuZhiliang    1.0        1.0 Version
 * </pre>
 */
public class ReflectUtil {

	/**
	 *
	 */
	public ReflectUtil() {
	}

	/**
	 * Description:通过反射调用get方法。
	 * @param obj 对象
	 * @param s 属性
	 * @return Object
	 * @throws
	 * @Author HuZhiliang
	 * Create Date: 2013-1-5 上午11:49:34
	 */
	public static Object invokeGetMethod(Object obj, String s) {
		try {
			String s1 = (new StringBuilder("get")).append(StringUtils.capitalize(s)).toString();
			Method method = obj.getClass().getMethod(s1, new Class[0]);
			return method.invoke(obj, new Object[0]);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * Description:通过反射调用set方法。
     * @param obj 对象
     * @param s 属性
     * @param obj1 对象
	 * @throws
	 * @Author HuZhiliang
	 * Create Date: 2013-1-5 上午11:50:45
	 */
	public static void invokeSetMethod(Object obj, String s, Object obj1) {
		Class<?> class1 = obj1.getClass();
		invokeSetMethod(obj, s, obj1, class1);
	}

	/**
	 * Description:通过反射调用set方法。
     * @param obj 对象
     * @param s 属性
     * @param obj1 对象
     * @param class1 类型
	 * @throws
	 * @Author HuZhiliang
	 * Create Date: 2013-1-5 上午11:51:12
	 */
	public static void invokeSetMethod(Object obj, String s, Object obj1, Class<?> class1) {
		String s1 = (new StringBuilder("set")).append(StringUtils.capitalize(s)).toString();
		try {
			Method method = obj.getClass().getMethod(s1, new Class[] { class1 });
			method.invoke(obj, new Object[] { obj1 });
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Description:通过属性名称，获取属性值。
     * @param obj 对象
     * @param s 属性名称。
	 * @return Object
	 * @throws
	 * @Author HuZhiliang
	 * Create Date: 2013-1-5 上午11:52:09
	 */
	public static Object getFieldValue(Object obj, String s) {
		Field field = getFiled(obj, s);
		if (field == null)
		{
			throw new IllegalArgumentException((new StringBuilder("Could not find field ")).append(s).toString());
		}
		Object obj1 = null;
		try {
			obj1 = field.get(obj);
		} catch (IllegalAccessException illegalaccessexception) {
		}
		return obj1;
	}

	/**
	 * Description:通过属性名称，为新对象设置属性值。
	 * @param obj 对象
     * @param s 属性名称。
     * @param obj1 新对象
	 * @throws
	 * @Author HuZhiliang
	 * Create Date: 2013-1-5 上午11:52:26
	 */
	public static void setFieldValue(Object obj, String s, Object obj1) {
		Field field = getFiled(obj, s);
		if (field == null)
		{
			throw new IllegalArgumentException((new StringBuilder("Could not find field ")).append(s).toString());
		}
		try {
			Class<?> type = field.getType();
			if (type.equals(Integer.class)) {
				field.set(obj, Integer.valueOf(obj1.toString()));
			} else if (type.equals(Boolean.class)) {
				field.set(obj, Boolean.valueOf(obj1.toString()));
			} else if (type.equals(Long.class)) {
				field.set(obj, Long.valueOf(obj1.toString()));
			} else {
				field.set(obj, obj1);
			}
		} catch (IllegalAccessException illegalaccessexception) {
		}
	}

	/**
	 * Description:通过属性名称获取对象属性。
     * @param obj 对象
	 * @param s 属性名称
	 * @return Field
	 * @throws
	 * @Author HuZhiliang
	 * Create Date: 2013-1-5 上午11:52:34
	 */
	private static Field getFiled(Object obj, String s) {
		Class<?> class1 = obj.getClass();
		Field field = null;
		while (class1 != Object.class) {

			try {
				field = class1.getDeclaredField(s);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {
				class1 = class1.getSuperclass();
			}
		}
		return field;
	}

	/**
	 * Description:获取对象属性。
	 * @param obj 对象
	 * @return Field[]
	 * @throws
	 * @Author HuZhiliang
	 * Create Date: 2013-1-5 上午11:52:38
	 */
	public static List<Field> getFields(Object obj) {
		Class<?> class1 = obj.getClass();
		Field[] filelds = null;
		List<Field> list = new ArrayList<Field>();
		while (class1 != Object.class) {
				filelds =class1.getDeclaredFields();
				list.addAll(Arrays.asList(filelds));
				class1 = class1.getSuperclass();
		}
		return list;
	}
	
	/**
	 * 反序列化具体的类信息
	 * 
	 * @param className
	 *            String
	 * @return Object
	 */
	public static Object loadClassObject(String className) {
		Object object = null;
		try {
			object = Class.forName(className).newInstance();
		} catch (ClassNotFoundException ex) {
			object = null;
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			object = null;
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			object = null;
			ex.printStackTrace();
		}
		return object;
	}
}
