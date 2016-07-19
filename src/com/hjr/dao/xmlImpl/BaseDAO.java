package com.hjr.dao.xmlImpl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.hjr.utils.PropertiesUtils;
import com.hjr.utils.ReflectionUtils;
import com.hjr.utils.StringUtils;
import com.hjr.utils.XmlUtils;

public class BaseDAO {

	private static File dataSource;
	private static Document doc;
	private static String DOMAIN_PACKEGE;

	/**
	 * 加载XML数据文件:如果无法从app.properties中读取到文件信息,并将文件加载到doc对象
	 */
	static {
		try {
			Properties proper = PropertiesUtils
					.load(BaseDAO.class.getResourceAsStream("/com/hjr/dao/xmlImpl/impl.properties"));
			String filePath = proper.getProperty("dataSource");
			DOMAIN_PACKEGE = proper.getProperty("domainPackege");
			dataSource = new File(filePath);
			doc = XmlUtils.load(dataSource);
		} catch (IOException e) {
			e.printStackTrace();

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public Document getDocument() {
		return doc;
	}

	public Element getRoot() {
		return doc.getRootElement();
	}

	public void save() throws IOException {
		XmlUtils.save(dataSource, doc);
	}

	public Element query(String condition) {
		Element ele = null;
		if (null != condition) {
			ele = XmlUtils.query(getRoot(), condition);
		}
		return ele;
	}

	public List<Element> queryList(String condition) {
		List<Element> list = null;
		if (null != condition) {
			list = XmlUtils.queryList(getRoot(), condition);
		}
		return list;
	}

	public Object parse2Bean(Element ele) throws Exception {
		if (null == ele) {
			return null;
		}
		Object bean = null;
		String nodeName = ele.getName();
		String className = new StringBuilder(DOMAIN_PACKEGE).append('.').append(nodeName).toString();
		Class<?> cls = Class.forName(className);
		if (null != cls) {
			bean = cls.newInstance();
			Map<String, Class<?>> fieldTypes = ReflectionUtils.fieldTypesMap(cls);
			if(fieldTypes.size() == 0) {
				return bean;
			}
			for (String f : fieldTypes.keySet()) {
				Class<?> type = fieldTypes.get(f);
				String setterName = StringUtils.getSetterName(f);
				Method method = ReflectionUtils.getMethod(cls, setterName, type);
				if (null != method) {
					method.invoke(bean, StringUtils.parseStr2Obj(ele.attributeValue(f), fieldTypes.get(f)));
				}
			}
		}
		return bean;
	}
}
