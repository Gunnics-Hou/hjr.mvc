package com.hjr.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
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
