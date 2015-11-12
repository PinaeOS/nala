package org.pinae.nala.xb.unmarshaller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.pinae.nala.xb.TestConstant;
import org.pinae.nala.xb.data.annotation.People;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;

/**
 * AnnotationXmlUnmarshaller单元测试
 * 
 * @author Huiyugeng
 * 
 */
public class AnnotationXmlUnmarshallerTest {

	/**
	 * 带注释的对象绑定XML文本
	 */
	@Test
	public void testBindAnnotationObject() {
		Unmarshaller bind = null;
		try {
			bind = new XmlUnmarshaller(new ResourceReader().getFileStream(TestConstant.TEST_XMLFILE1));
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		bind.setRootClass(People.class);
		People people = new People();
		try {
			people = (People) bind.unmarshal();
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		assertEquals(people.getPerson().size(), 2);
	}
}
