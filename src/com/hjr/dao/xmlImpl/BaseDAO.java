package com.hjr.dao.xmlImpl;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.hjr.utils.PropertiesUtils;
import com.hjr.utils.XmlUtils;

public class BaseDAO {

	private static File dataSource;
	private static Document doc;

	/**
	 * 加载XML数据文件:如果无法从app.properties中读取到文件信息,并将文件加载到doc对象
	 */
	static {
		try {
			Properties proper = PropertiesUtils
					.load(BaseDAO.class.getResourceAsStream("/com/hjr/dao/xmlImpl/app.properties"));
			String filePath = proper.getProperty("dataSource");
			dataSource = new File(filePath);
			doc = XmlUtils.load(dataSource);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public Document load() {
		return doc;
	}

	public void save() throws IOException {
		XmlUtils.save(dataSource, doc);
	}

}
