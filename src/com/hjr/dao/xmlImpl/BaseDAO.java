package com.hjr.dao.xmlImpl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.hjr.exception.MsgException;
import com.hjr.utils.PropertiesUtils;
import com.hjr.utils.ReflectionUtils;
import com.hjr.utils.StringUtils;
import com.hjr.utils.XmlUtils;

public class BaseDAO {

	private static final File DATA_SOURCE;
	private static final String DOMAIN_PACKEGE;
	private static final String PROPERTIES_PATH = "/com/hjr/dao/xmlImpl/impl.properties";
	private static Document doc;

	/**
	 * 加载XML数据文件:app.properties中读取到文件信息,并将文件加载到doc对象
	 */
	static {
		Properties proper = null;
		try {
			proper = PropertiesUtils.load(BaseDAO.class
					.getResourceAsStream(PROPERTIES_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String filePath = proper.getProperty("dataSource");
		DOMAIN_PACKEGE = proper.getProperty("domainPackege");
		DATA_SOURCE = new File(filePath);
		try {
			doc = XmlUtils.load(DATA_SOURCE);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		if (null == DATA_SOURCE || null == DOMAIN_PACKEGE) {
			throw new RuntimeException("配置文件出错");
		}
	}

	/**
	 * @return 数据文件的document对象
	 */
	public Document getDocument() {
		return doc;
	}

	/**
	 * @return 数据文件的根节点
	 */
	public Element getRoot() {
		return doc.getRootElement();
	}

	/**
	 * 向数据文件添加一条记录
	 * 
	 * @param beanName
	 *            节点名称
	 * @param params
	 *            属性集
	 * @throws MsgException
	 */
	public void add(String beanName, Map<String, Object> params)
			throws MsgException {
		Element table = getBeanTalble(beanName);
		if (null == table) {
			throw new MsgException("");
		}
		Element beanElem = table.addElement(beanName);
		for (String key : params.keySet()) {
			beanElem.addAttribute(key, parse2String(params.get(key)));
		}
		save();
	}

	/**
	 * 更新一条记录
	 * 
	 * @param beanName
	 *            节点名
	 * @param id
	 * @param params
	 *            需更新的参数集合
	 * @throws MsgException
	 */
	public void update(String beanName, String id, Map<String, Object> params)
			throws MsgException {
		if (null == id) {
			throw new IllegalArgumentException("id字段不存在");
		}
		if (null == params || params.size() == 0) {
			throw new IllegalArgumentException("无更新项目");
		}
		Element targetElem = (Element) doc.selectSingleNode(new StringBuilder(
				"//").append(beanName).append("[@id='").append(id).append("']")
				.toString());

		if (params.keySet().contains("id")) {
			params.remove("id");
		}
		if (null != targetElem && params.size() > 0) {
			for (String key : params.keySet()) {
				Attribute attr;
				String attrVal = parse2String(params.get(key));
				if (null == (attr = targetElem.attribute(key))
						|| attr.getValue().equals(attrVal)) {
					continue;
				}
				attr.setValue(attrVal);
			}
		}
		save();
	}

	/**
	 * 按照指定条件移除节点
	 * 
	 * @param condition
	 *            移除条件
	 * @throws MsgException
	 */
	public void remove(String condition) throws MsgException {
		List<?> elems = doc.selectNodes(condition);
		if (null != elems && elems.size() > 0) {
			for (Object ele : elems) {
				if (ele instanceof Element)
					doc.remove((Element) ele);
			}
		}
		save();
	}

	/**
	 * 通过id删除记录
	 * 
	 * @param beanName
	 * @param id
	 * @throws MsgException
	 */
	public void removeById(String beanName, String id) throws MsgException {
		if (null == id) {
			throw new IllegalArgumentException("id字段不存在");
		}
		remove(new StringBuilder("//").append(beanName).append("[@id='")
				.append(id).append("']").toString());
	}

	/**
	 * 保存document对象到文件
	 * 
	 * @throws MsgException
	 */
	private void save() throws MsgException {
		try {
			XmlUtils.save(DATA_SOURCE, doc);
		} catch (IOException e) {
			throw new MsgException(e.getMessage());
		}
	}

	public Object query(String condition) {
		Object result = null;
		Element ele = (Element) doc.selectSingleNode(condition);
		try {
			result = parse2Bean(ele);
		} catch (Exception e) {
		}
		return result;
	}

	public List<Object> queryList(String condition) {
		List<?> list = doc.selectNodes(condition);
		if (null == list || list.size() == 0) {
			return null;
		}
		List<Object> results = new ArrayList<Object>(list.size());
		for (Object obj : list) {
			if (obj instanceof Element) {
				try {
					results.add(parse2Bean((Element) obj));
				} catch (Exception e) {
				}
			}
		}
		return results;
	}

	/**
	 * @param beanName
	 * @return
	 */
	private Element getBeanTalble(String beanName) {
		Element tableElem = null;
		if (null != beanName && !"".equals(beanName.trim())) {
			tableElem = (Element) getRoot().selectSingleNode(
					beanName + "-table[1]");
		}
		return tableElem;
	}

	private String parse2String(Object obj) {
		if (obj instanceof java.util.Date) {
			return StringUtils.parseDate2Str((Date) obj, null);
		}
		return obj.toString();
	}

	/**
	 * 将element叶子节点转化为bean
	 * 
	 * @param ele
	 * @return
	 * @throws Exception
	 */
	private Object parse2Bean(Element ele) throws Exception {
		if (null == ele) {
			return null;
		}
		Object bean = null;
		String nodeName = ele.getName();
		String className = new StringBuilder(DOMAIN_PACKEGE).append('.')
				.append(nodeName).toString();
		Class<?> cls = Class.forName(className);
		if (null != cls) {
			bean = cls.newInstance();
			Map<String, Class<?>> fieldTypes = ReflectionUtils
					.fieldTypesMap(cls);
			if (fieldTypes.size() == 0) {
				return bean;
			}
			for (String f : fieldTypes.keySet()) {
				Class<?> type = fieldTypes.get(f);
				String setterName = StringUtils.getSetterName(f);
				Method method = ReflectionUtils
						.getMethod(cls, setterName, type);
				if (null != method) {
					method.invoke(bean, StringUtils.parseStr2Obj(
							ele.attributeValue(f), fieldTypes.get(f)));
				}
			}
		}
		return bean;
	}
}
