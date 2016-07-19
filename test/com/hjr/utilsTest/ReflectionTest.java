package com.hjr.utilsTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hjr.domain.User;
import com.hjr.utils.ReflectionUtils;

public class ReflectionTest {
	
	public Class<?> cls = User.class;
	
	@Test
	public void testField() {
		Field[] fs = ReflectionUtils.getDeclaredFields(cls);
		for(Field f : fs) {
			System.out.println(f.getName());
		}
	}
	
	@Test
	public void testInstance() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", 123);
		map.put("name", "admin");
		try {
			Object o1 = ReflectionUtils.getInstance(cls, null);
			Object o2 = ReflectionUtils.getInstance(cls, map);
			System.out.println(o1);
			System.out.println(o2);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMethod() {
		try {
			Demo demo = new Demo();
			Method method = ReflectionUtils.getMethod(Demo.class, "m3", String.class,int.class);
			if(null != method) {
				method.invoke(demo, "admin",123);
			}
			
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

}

class Demo {
	
	public void m1(String args) {
		System.out.println(args);
	}
	public void m2(String arg1,int arg2) {
		System.out.println(arg1 + arg2);
	}
}
