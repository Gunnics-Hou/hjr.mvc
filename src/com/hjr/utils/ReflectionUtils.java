package com.hjr.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {

	/**
	 * 获取类的属性数组
	 * 
	 * @param cls
	 * @return
	 */
	public static Field[] getDeclaredFields(Class<?> cls) {
		if (null != cls) {
			return cls.getDeclaredFields();
		}
		return null;
	}

	/**
	 * 获取一个类的属性名和属性类组成的map
	 * 
	 * @param cls
	 * @return
	 */
	public static Map<String, Class<?>> fieldTypesMap(Class<?> cls) {
		if (null == cls) {
			return null;
		}
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		Field[] fields = getDeclaredFields(cls);
		if (null != fields && fields.length > 0) {
			for (Field f : fields) {
				map.put(f.getName(), f.getType());
			}
		}
		return map;
	}

	/**
	 * 获取无参构造器
	 * 
	 * @param cls
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Constructor<?> getNonArgConstuctor(Class<?> cls)
			throws ReflectiveOperationException {
		if (null != cls) {
			return cls.getDeclaredConstructor();
		}
		return null;
	}

	/**
	 * 获取方法
	 * 
	 * @param cls
	 * @param name
	 * @param parameterTypes
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Method getMethod(Class<?> cls, String name,
			Class<?>... parameterTypes) throws ReflectiveOperationException {
		Method method = null;
		if (containsSuchMethod(cls, name, parameterTypes)) {
			method = cls.getMethod(name, parameterTypes);
		}
		return method;
	}

	/**
	 * 判断一个类是否包含某个方法
	 * 
	 * @param cls
	 *            类名
	 * @param name
	 *            方法名
	 * @param parameterTypes
	 *            方法的参数
	 * @return
	 */
	public static boolean containsSuchMethod(Class<?> cls, String name,
			Class<?>... parameterTypes) {
		if (null == cls || null == name) {
			return false;
		}
		Method[] ms = cls.getDeclaredMethods();
		if (ms.length > 0) {
			for (Method m : ms) {
				if (m.getName().equals(name)) {
					Class<?>[] ps = m.getParameterTypes();
					int len = ps.length;
					if (len == 0 && parameterTypes == null) {
						return true;
					} else if (null != parameterTypes
							&& len == parameterTypes.length) {
						for (int i = 0; i < len; i++) {
							if (!(ps[i] == parameterTypes[i])) {
								return false;
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 通过类和属性键值对map加载一个实例
	 * 
	 * @param cls
	 *            类名
	 * @param params
	 *            属性键值对
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Object getInstance(Class<?> cls, Map<String, Object> params)
			throws ReflectiveOperationException {
		if (cls == null) {
			return null;
		}
		Object obj = cls.newInstance();
		if (null == params || params.size() == 0) {
			return obj;
		}
		for (String paramName : params.keySet()) {
			String setterName = StringUtils.getSetterName(paramName);
			if (null != setterName) {
				Object valueObj = params.get(paramName);
				getMethod(cls, setterName, valueObj.getClass()).invoke(obj,
						valueObj);
			}
		}
		return obj;
	}

	public static Map<String, Object> getBeanAttrPairs(Object bean)
			throws ReflectiveOperationException {
		if (null == bean) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] attrs = bean.getClass().getDeclaredFields();
		if (null != attrs && attrs.length > 0) {
			for (Field f : attrs) {
				map.put(f.getName(), f.get(bean));
			}
		}
		return map;
	}
}
