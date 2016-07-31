package com.hjr.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {

	/**
	 * 
	 * @param file
	 *            XML文件
	 * @return 当参数不为空且是合法文件时，返回document对象
	 * @throws DocumentException
	 */
	public static Document load(File file) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}

	/**
	 * 查询单个节点
	 * 
	 * @param parent
	 * @param condition
	 * @return
	 */
	public static Element query(Element parent, String condition) {
		Element ele = null;
		if (null != parent) {
			Node node = parent.selectSingleNode(condition);
			if (node instanceof Element) {
				ele = (Element) node;
			}
		}
		return ele;
	}

	/**
	 * 
	 * @param parent
	 * @param condition
	 * @return
	 */
	public static List<Element> queryList(Element parent, String condition) {
		List<Element> list = null;
		if (null != parent) {
			List<?> nodeList = parent.selectNodes(condition);
			list = new ArrayList<Element>(nodeList.size());
			for (Object node : nodeList) {
				if (node instanceof Element) {
					list.add((Element) node);
				}
			}
		}
		return list;
	}

	public static void addElement(Element parent, String eleName, Map<String, Object> map) {
		if (null == parent || null == eleName) {
			return;
		}
		Element element = parent.addElement(eleName);
		if (null != map && map.size() > 0) {
			for (String key : map.keySet()) {
				element.addAttribute(key, map.get(key).toString());
			}
		}
	}

	public void editElement(Element ele, Map<String, String> params) {
		if (null == ele) {
			return;
		}
		if (null != params && params.size() > 0) {
			for (String key : params.keySet()) {
				Attribute attr;
				String attrVal;
				if (null != (attr = ele.attribute(key)) && attr.getValue() != (attrVal = params.get(key))) {
					attr.setValue(attrVal);
				}
			}
		}
	}

	public void removeElement(Element parent, String condition) {
		List<?> eles = parent.selectNodes(condition);
		if (null != eles && eles.size() > 0) {
			for (Object o : eles) {
				eles.remove(o);
			}
		}
	}

	/**
	 * 
	 * @param in
	 *            XML输入流
	 * @return 当参数不为空且合法时，返回document对象
	 * @throws DocumentException
	 */
	public static Document load(InputStream in) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(in);
		return document;
	}

	/**
	 * 将Document对象写入XML文件
	 * 
	 * @param file
	 *            XML文件
	 * @param document
	 *            Document对象
	 * @throws IOException
	 */
	public static void save(File file, Document document) throws IOException {
		XMLWriter writer = new XMLWriter(new FileWriter(file));
		writer.write(document);
		writer.close();
	}
}
