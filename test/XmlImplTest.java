import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Test;

import com.hjr.dao.xmlImpl.BaseDAO;

public class XmlImplTest {

	private BaseDAO baseDAO = new BaseDAO();

	@Test
	public void testLoad() {
		Document doc = baseDAO.getDocument();
		Element root = baseDAO.getRoot();
		Assert.assertNotNull(doc);
		Assert.assertNotNull(root);
		Assert.assertEquals("data",root.getName());
	}
	
	@Test
	public void testQuery() {
		Element ele = baseDAO.query("user[1]");
		System.out.println(ele);
	}
	
	@Test
	public void testQueryList() {
		
		List<Element> list = baseDAO.queryList("user");
		System.out.print(list.size());
	}
}

