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
	public static Constructor<?> getNonArgConstuctor(Class<?> cls) throws ReflectiveOperationException {
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
	public static Method getMethod(Class<?> cls, String name, Class<?>... parameterTypes)
			throws ReflectiveOperationException {
		Method method = null;
		if (containsSuchMethod(cls, name, parameterTypes)) {
			method = cls.getMethod(name, parameterTypes);
		}
		return method;
	}

	public static boolean containsSuchMethod(Class<?> cls, String name, Class<?>... parameterTypes) {
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
					} else if (null != parameterTypes && len == parameterTypes.length) {
						for (int i = 0; i < len; i++) {
							if (!(ps[i]==parameterTypes[i])) {
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
	 * 加载实例
	 * 
	 * @param cls
	 * @param params
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Object getInstance(Class<?> cls, Map<String, Object> params) throws ReflectiveOperationException {
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
				getMethod(cls, setterName, valueObj.getClass()).invoke(obj, valueObj);
			}
		}
		return obj;
	}
}
