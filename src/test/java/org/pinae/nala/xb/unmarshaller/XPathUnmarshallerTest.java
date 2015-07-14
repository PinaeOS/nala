package org.pinae.nala.xb.unmarshaller;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.pinae.nala.xb.NalaTestConstant;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.resource.bean.Person;
import org.pinae.nala.xb.unmarshal.XPathUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;


/**
 * org.pinae.nala.xb.unmarshaller测试
 * 
 * @author Huiyugeng
 * 
 */
public class XPathUnmarshallerTest extends TestCase {
	private static final Logger log = Logger.getLogger(XPathUnmarshallerTest.class);
	
	/**
	 * 测试通过XPath绑定指定对象, XPath不允许直接绑定内部类
	 */
	@SuppressWarnings("rawtypes")
	public void testFindObjectByXPath() {
		XPathUnmarshaller bind = null;
		try {
			bind = new XPathUnmarshaller(new ResourceReader().getFileStream
											(NalaTestConstant.TEST_XPATH), 
										"//people/person");
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
