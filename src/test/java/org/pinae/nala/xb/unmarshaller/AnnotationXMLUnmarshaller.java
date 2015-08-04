package org.pinae.nala.xb.unmarshaller;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.pinae.nala.xb.NalaTestConstant;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.resource.annotation.People;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XMLUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;
/**
 * org.pinae.nala.xb.unmarshaller测试
 * 
 * @author Huiyugeng
 * 
 */
public class AnnotationXMLUnmarshaller  extends TestCase {
	private static final Logger log = Logger.getLogger(AnnotationXMLUnmarshaller.class);
	
	/**
	 * 带注释的对象绑定XML文本
	 */
	public void testBindAnnotationObject(){
		Unmarshaller bind = null;
		try {
			bind = new XMLUnmarshaller(new ResourceReader().getFileStream(NalaTestConstant.TEST_XMLFILE1));
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
