package org.pinae.nala.xb.marshaller;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.pinae.nala.xb.TestConstant;
import org.pinae.nala.xb.data.bean.People;
import org.pinae.nala.xb.data.bean.PeopleTwo;
import org.pinae.nala.xb.data.bean.Person;
import org.pinae.nala.xb.data.bean.PersonOne;
import org.pinae.nala.xb.data.bean.PersonThree;
import org.pinae.nala.xb.data.bean.PersonTwo;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XmlMarshaller;
import org.pinae.nala.xb.util.ResourceWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XMLMarshaller单元测试
 * 
 * @author Huiyugeng
 *
 */
public class XmlMarshallerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlMarshallerTest.class);
	
	private People people = new People();
	
	@Before
	public void setUp(){
		people.setPerson((Person)PersonOne.getObject());
		people.setPerson((Person)PersonTwo.getObject());
		people.setPerson((Person)PersonThree.getObject());
	}
	
	/**
	 * 测试从对象生成XML文件(编组)
	 */
	@Test
	public void testMarshal() {
		Marshaller marshaller = new XmlMarshaller(people);

		marshaller.setDocumentStart("<!-- Object to XML -->");
		marshaller.enableNodeMode(true);
		marshaller.enableLowCase(true);
		
		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), TestConstant.OUTPUT_XMLFILE);
			logger.debug(marshaller.marshal().toString());
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (MarshalException e) {
			fail(e.getMessage());
		}

	}
	
	/**
	 * 测试从对象（XML对象）生成XML文件（编组）
	 */
	@Test
	public void testMarshallerXMLInXmlObject(){
		Marshaller marshaller = new XmlMarshaller(new PeopleTwo());

		marshaller.setDocumentStart("<!-- XML Object to XML -->");
		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), TestConstant.OUTPUT_XMLFILE);
			logger.debug(marshaller.marshal().toString());
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (MarshalException e) {
			fail(e.getMessage());
		}
		
	}
	

}
