package org.pinae.nala.xb.unmarshaller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.pinae.nala.xb.TestConstant;
import org.pinae.nala.xb.data.bean.Person;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.unmarshal.XPathUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;

/**
 * XPathUnmarshaller单元测试
 * 
 * @author Huiyugeng
 * 
 */
public class XPathUnmarshallerTest {

	/**
	 * 测试通过XPath绑定指定对象, XPath不允许直接绑定内部类
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testFindObjectByXPath() {
		XPathUnmarshaller bind = null;
		try {
			bind = new XPathUnmarshaller(new ResourceReader().getFileStream(TestConstant.TEST_XPATH), "//people/person");
			bind.setRootClass(Person.class);
			List lstSex = bind.unmarshal();
			assertEquals(lstSex.size(), 2);
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
	}

}
