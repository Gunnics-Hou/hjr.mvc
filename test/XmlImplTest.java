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
	}
	
	@Test
	public void testQuery() {
		Element ele = baseDAO.query("//user[@id='001']");
		System.out.print(ele.getText());
	}
}
