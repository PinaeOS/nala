package org.pinae.nala.xb.unmarshaller;

import org.pinae.nala.xb.NalaTestConstant;
import org.pinae.nala.xb.data.bean.People;
import org.pinae.nala.xb.data.bean.PeopleInXml;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;

import junit.framework.TestCase;

/**
 * XmlUnmarshaller单元测试
 * 
 * @author Huiyugeng
 * 
 */
public class XmlUnmarshallerTest extends TestCase {

	/**
	 * 测试绑定整个XML
	 */
	public void testBindObject() {
		People people = new People();
		
		try {
			Unmarshaller bind = new XmlUnmarshaller(new ResourceReader()
					.getFileStream(NalaTestConstant.TEST_XMLFILE1));
			
			bind.setRootClass(People.class);

			people = (People) bind.unmarshal();
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}

		assertEquals(people.getPerson().size(), 2);
	}

	/**
	 * 测试在对象中绑定XML文本
	 */
	public void testBindObjectInXml() {
		PeopleInXml people = new PeopleInXml();
		
		try {
			Unmarshaller bind = new XmlUnmarshaller(new ResourceReader()
					.getFileStream(NalaTestConstant.TEST_XMLFILE1));
			
			bind.setRootClass(PeopleInXml.class);
			
			people = (PeopleInXml) bind.unmarshal();
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}

		assertEquals(people.getPerson().size(), 2);
	}
	

	

}
