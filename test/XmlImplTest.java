import org.junit.Test;

import com.hjr.dao.xmlImpl.BaseDAO;

public class XmlImplTest {
	
	private BaseDAO baseDAO = new BaseDAO();
	
	@Test
	public void testLoad() {
		baseDAO.load();
	}
}
