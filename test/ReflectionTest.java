import java.lang.reflect.Field;

import org.junit.Test;

import com.hjr.domain.User;

public class ReflectionTest {
	
	public Class<?> cls = User.class;

	@Test
	public void test1() {
	
		try {
			User u1 = (User) cls.newInstance();
			u1.setId(100);
			System.out.println(u1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		Field[] fs = cls.getDeclaredFields();
		for (Field f : fs) {
			System.out.print(f.getName());
		}
	}
	
	@Test
	public void test3() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
